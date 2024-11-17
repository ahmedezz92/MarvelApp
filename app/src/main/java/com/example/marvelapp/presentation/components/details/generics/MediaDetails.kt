package com.example.marvelapp.presentation.components.details.generics

import com.example.marvelapp.domain.model.Thumbnail

data class MediaDetails(
    val id: Int,
    val title: String,
    val description: String?,
    val thumbnail: Thumbnail?
)
