package com.example.carmarketpalce

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestService {
    @GET("Cars")
    fun getCars(): Call<List<Cars>>

}