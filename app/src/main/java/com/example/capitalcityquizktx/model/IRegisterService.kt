package com.example.capitalcityquizktx.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IRegisterService {
    @POST("/users/sign-up")
    fun createUser(@Body userDetails: UserDetails) : Call<UserDetails>
}
