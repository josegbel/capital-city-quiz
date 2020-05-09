package com.example.capitalcityquizktx.model.register

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IUserManagementService {

    @POST("users/sign-up")
    fun createUser(@Body body: UserDetails) : Call<Boolean>

    @GET
    fun findByUsername(username: String) : Call<UserDetails>

    @GET("users/verify")
    fun verifyUserIsNotInDatabase(@Query("username") username: String,
                                  @Query("email") email: String) : Call<UserExistence>
}
