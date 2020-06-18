package com.example.capitalcityquizktx.data.network.register

import com.example.capitalcityquizktx.data.models.user.UserDetailsSchema
import com.example.capitalcityquizktx.data.models.user.UserExistenceSchema
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserManagementService {

    @POST("users/sign-up")
    suspend fun createUser(@Body body: UserDetailsSchema) : Boolean

    @GET
    fun findByUsername(username: String) : Call<UserDetailsSchema>

    @GET("users/verify")
    suspend fun verifyUserIsNotInDatabase(@Query("username") username: String,
                                  @Query("email") email: String) : UserExistenceSchema
}
