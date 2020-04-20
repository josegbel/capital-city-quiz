package com.example.capitalcityquizktx.ui

import android.util.Log
import com.example.capitalcityquizktx.model.IRegisterService
import com.example.capitalcityquizktx.model.UserDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

        val map = HashMap<String, Any>()
        map["username"] = userDetails.username
        map["password"] = userDetails.password
        map["email"] = userDetails.email
        map["name"] = userDetails.name
        map["surname"] = userDetails.surname

        val params = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(map).toString())

        val call = service.createUser(userDetails)

        call.enqueue(object : Callback<UserDetails?> {

            override fun onResponse(call: Call<UserDetails?>, response: Response<UserDetails?>) {
                val user1: UserDetails? = response.body()
                Log.d("RETRONET", "SUCCESS")
            }

            override fun onFailure(call: Call<UserDetails?>, t: Throwable) {
                call.cancel()
                Log.d("RETRONET", "FAILED")
            }
        })
        }
    }