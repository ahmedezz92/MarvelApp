package com.example.marvelapp.data.datasource.remote

import com.example.marvelapp.data.core.data.utils.WrappedResponse
import com.example.marvelapp.data.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface MarvelApi {
    @GET("characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String
    ): Response<WrappedResponse<CharactersResponse>>

    @GET
    suspend fun getResourceImage(
        @Url url: String,
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String
    ): Response<WrappedResponse<CharactersResponse>>
}