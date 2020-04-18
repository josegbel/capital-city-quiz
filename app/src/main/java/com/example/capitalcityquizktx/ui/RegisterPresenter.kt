package com.example.capitalcityquizktx.ui

import com.example.capitalcityquizktx.model.IRegisterService
import com.example.capitalcityquizktx.model.UserDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext


class RegisterPresenter (val view : IRegisterView){
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: IRegisterService = retrofit.create(IRegisterService::class.java)

    fun createNewUser(userDetails: UserDetails) {
//        scope.launch {
            val call = service.createUser(userDetails)
//        call.enqueue(object : Callback<UserDetails?> {
//
//            override fun onResponse(call: Call<UserDetails?>, response: Response<UserDetails?>) {
//                val user1: UserDetails? = response.body()
//                Toast.makeText(
//                    getApplicationContext(),
//                    user1?.username + " " + user1?.firstName,
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//
//            override fun onFailure(call: Call<UserDetails?>, t: Throwable) {
//                call.cancel()
//                Toast.makeText(
//                    getApplicationContext(),
//                    "FAILED TO CONNECT",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        })

//        }
    }


}