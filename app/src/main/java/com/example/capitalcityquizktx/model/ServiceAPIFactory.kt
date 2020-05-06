package com.example.capitalcityquizktx.model

import com.example.capitalcityquizktx.model.register.IUserManagementService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceAPIFactory {
    private const val API_BASE_URL = "http://10.0.2.2:8080/"

    fun createService(): IUserManagementService {
        val logging = HttpLoggingInterceptor()
        logging.apply { logging.level = HttpLoggingInterceptor.Level.BODY }
        val httpClient = OkHttpClient.Builder().addInterceptor(logging)

        val builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        return builder
            .client(httpClient.build())
            .build().create(IUserManagementService::class.java)
    }
}
