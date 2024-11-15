package com.example.marvelapp.domain.model

import com.example.marvelapp.data.core.data.utils.ErrorResponse

sealed class BaseResult<out T> {
    data class DataState<T : Any>(val items: T?) : BaseResult<T>()
    data class ErrorState(val errorResponse: ErrorResponse?) : BaseResult<Nothing>()
}