package com.example.capitalcityquizktx.ui

import android.util.Log
import com.example.capitalcityquizktx.model.ServiceAPIFactory
import com.example.capitalcityquizktx.model.UserDetails
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterPresenter (val view : IRegisterView){

    private val service = ServiceAPIFactory.createService()

    fun createNewUser(userDetails: UserDetails) {
        GlobalScope.launch {
            val call = service.createUser(userDetails)

            call.enqueue(object : Callback<UserDetails?> {
                override fun onResponse(
                    call: Call<UserDetails?>,
                    response: Response<UserDetails?>
                ) {
                    Log.d("RETRONET", "SUCCESS")
                }

                override fun onFailure(call: Call<UserDetails?>, t: Throwable) {
                    call.cancel()
                    Log.d("RETRONET", "FAILED")
                }
            })
        }
    }
}