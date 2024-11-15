package com.example.marvelapp.data.datasource.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET()
    suspend fun getMarvelList(
        @Query("key") key: String,
        @Query("q") query: String,
    ): Response<Any>
}