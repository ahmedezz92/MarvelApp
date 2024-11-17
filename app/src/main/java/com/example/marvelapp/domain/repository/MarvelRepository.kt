package com.example.marvelapp.domain.repository

import com.example.marvelapp.data.core.data.utils.WrappedResponse
import com.example.marvelapp.data.model.CharactersResponse
import com.example.marvelapp.domain.model.BaseResult
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {
    suspend fun getCharactersList(
        offset: Int,
        limit: Int
    ): Flow<BaseResult<WrappedResponse<CharactersResponse>>>

}