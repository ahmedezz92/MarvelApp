package com.example.marvelapp.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.data.core.data.utils.ErrorResponse
import com.example.marvelapp.data.model.CharactersResponse
import com.example.marvelapp.domain.model.BaseResult
import com.example.marvelapp.domain.model.Character
import com.example.marvelapp.domain.usecase.GetCharactersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase,
) : ViewModel() {

    private val _state =
        MutableStateFlow<GetCharacterState>(GetCharacterState.IsLoading)
    val state: StateFlow<GetCharacterState> =
        _state.asStateFlow()

    private val _characterList = MutableStateFlow<List<Character?>>(emptyList())
    val characterList: StateFlow<List<Character?>> = _characterList


    private var currentPage = 0
    private val pageSize = 20

    /*loading progress for loading state*/
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    /*error state*/
    private val _errorResponse = MutableStateFlow<ErrorResponse?>(null)
    val errorResponse: StateFlow<ErrorResponse?> = _errorResponse

    init {
        getCharactersList()
    }

    fun getCharactersList() {
        viewModelScope.launch {
            getCharactersListUseCase.execute(offset = currentPage * pageSize, limit = pageSize)
                .onStart {
                    setLoading()
                }
                .catch {
                    hideLoading()
                }
                .collect { result ->
                    hideLoading()
                    when (result) {
                        is BaseResult.ErrorState -> {
                            _errorResponse.value = result.errorResponse
                        }

                        is BaseResult.DataState -> {
                            currentPage++
                            result.items?.data?.results?.let { newResults ->
                                val updatedList = _characterList.value + newResults
                                _characterList.value = updatedList
                            }
                        }
                    }
                }
        }
    }


    private fun setLoading() {
        _isLoading.value = true
    }

    private fun hideLoading() {
        _isLoading.value = false
    }
}

sealed class GetCharacterState {
    object IsLoading : GetCharacterState()
    data class Success(val data: CharactersResponse) : GetCharacterState()
    data class Error(val errorResponse: ErrorResponse) : GetCharacterState()
}