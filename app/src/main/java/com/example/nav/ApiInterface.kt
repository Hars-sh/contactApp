package com.example.nav

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("users")
    suspend fun getData():Response<MyData>
}