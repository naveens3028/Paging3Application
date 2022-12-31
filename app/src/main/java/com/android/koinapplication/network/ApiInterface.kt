package com.android.koinapplication.network

import com.android.koinapplication.model.ExampleJson2KtKotlin
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("users")
    suspend fun getUserData(@Query("page") page : Int): ExampleJson2KtKotlin

}