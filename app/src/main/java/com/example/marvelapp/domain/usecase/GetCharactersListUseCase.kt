package com.example.marvelapp.domain.usecase

import com.example.marvelapp.data.core.data.utils.WrappedResponse
import com.example.marvelapp.data.model.CharactersResponse
import com.example.marvelapp.domain.model.BaseResult
import com.example.marvelapp.domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersListUseCase @Inject constructor(private val marvelRepository: MarvelRepository) {
    suspend fun execute(offset: Int, limit: Int): Flow<BaseResult<WrappedResponse<CharactersResponse>>> {
        return marvelRepository.getCharactersList(offset, limit)
    }
}