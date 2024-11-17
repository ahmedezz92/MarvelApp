package com.example.marvelapp.presentation.components.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.domain.model.BaseResult
import com.example.marvelapp.domain.usecase.GetComicImageUseCase
import com.example.marvelapp.presentation.components.details.generics.MediaDetails
import com.example.marvelapp.presentation.components.details.generics.MediaItem
import com.example.marvelapp.presentation.components.details.generics.MediaType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getComicImageUseCase: GetComicImageUseCase
) : ViewModel() {

    private val _mediaState = MutableStateFlow<Map<MediaType, List<MediaDetails>>>(emptyMap())
    val mediaState: StateFlow<Map<MediaType, List<MediaDetails>>> = _mediaState

    private var isLoadingComics = false

    private val _loadingStates = MutableStateFlow<Map<MediaType, Boolean>>(emptyMap())
    val loadingStates: StateFlow<Map<MediaType, Boolean>> = _loadingStates

    private fun setLoading(mediaType: MediaType) {
        _loadingStates.value = _loadingStates.value + (mediaType to true)
    }

    private fun hideLoading(mediaType: MediaType) {
        _loadingStates.value = _loadingStates.value + (mediaType to false)
    }

    fun <T : MediaItem> loadMediaItems(items: List<T>, mediaType: MediaType) {
        viewModelScope.launch {
            val mediaDetails = mutableListOf<MediaDetails>()
            setLoading(mediaType)
            try {
                items.forEach { item ->
                    getComicImageUseCase.execute(item.resourceURI)
                        .collect { result ->
                            when (result) {
                                is BaseResult.DataState -> {
                                    result.items?.data?.results?.firstOrNull()?.let {
                                        mediaDetails.add(
                                            MediaDetails(
                                                it.id,
                                                it.title!!,
                                                it.description,
                                                it.thumbnail
                                            )
                                        )
                                        _mediaState.value =
                                            _mediaState.value + (mediaType to mediaDetails.toList())
                                    }
                                }

                                is BaseResult.ErrorState -> {
                                    hideLoading(mediaType)
                                } // Handle error
                            }
                        }
                }
            } finally {
                hideLoading(mediaType)
            }
        }
    }

    sealed class LazyLoadState<out T> {
        object Initial : LazyLoadState<Nothing>()
        data class Success<T>(
            val items: List<T>,
            val isAllLoaded: Boolean
        ) : LazyLoadState<T>()

        data class Error(val message: String) : LazyLoadState<Nothing>()
    }
}