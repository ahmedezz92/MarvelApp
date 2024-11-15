package com.example.marvelapp.data.repository

import com.example.marvelapp.data.datasource.remote.MarvelApi
import com.example.marvelapp.domain.repository.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val api: MarvelApi
) : MarvelRepository {
}