package com.example.capitalcityquizktx.ui.register

import android.util.Log
import com.example.capitalcityquizktx.model.ServiceAPIFactory
import com.example.capitalcityquizktx.model.register.UserDetails
import com.example.capitalcityquizktx.model.register.UserExistence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterPresenter (val view : IRegisterView){

    private val service = ServiceAPIFactory.createService()

    fun createNewUser(userDetails: UserDetails) {

        GlobalScope.launch (Dispatchers.Default) {
            // confirm email and username does not exist in database
            val call = service.verifyUserIsNotInDatabase(userDetails.username, userDetails.email)

            call.enqueue(object : Callback<UserExistence?> {
                override fun onResponse(
                    call: Call<UserExistence?>,
                    response: Response<UserExistence?>
                ) {
                    if(response.isSuccessful) {
                        if (response.body()!!.emailInDatabase) {
                            // Notify UI about existing email
                            view.emailIsInDatabaseValidation()
                        }

                        if (response.body()!!.usernameInDatabase) {
                            // Notify UI about existing username
                            view.usernameInDatabaseValidation()
                        }

                        if(!response.body()!!.usernameInDatabase &&
                            !response.body()!!.emailInDatabase){
                            val call = service.createUser(userDetails)

                            call.enqueue(object : Callback<Boolean?> {
                                override fun onResponse(
                                    call: Call<Boolean?>,
                                    response: Response<Boolean?>
                                ) {
                                    if(response.body()!!) {
                                        Log.d("RETRONET", "USER CREATED")
                                        // go to success fragment
                                    }
                                    else {
                                        Log.d("RETRONET", "USER WAS NOT CREATED")
                                        view.displayAccountErrorDialog()
                                    }
                                }

                                override fun onFailure(call: Call<Boolean?>, t: Throwable) {
                                    view.displayUnableToConntectDialog()
                                    Log.d("RETRONET", "FAILED TO CONNECT CREATING USER")
                                    call.cancel()
                                }
                            })
                        }
                    }
                }

                override fun onFailure(call: Call<UserExistence?>, t: Throwable) {
                    call.cancel()
                    Log.d("RETRONET", "FAILED TO CONNECT CHECKING USER EXISTENCE")
                    view.displayUnableToConntectDialog()
                }
            })
        }
    }
}