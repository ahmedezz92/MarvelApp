package com.example.marvelapp.domain.model

import com.example.marvelapp.presentation.components.details.generics.MediaItem

data class Comic(
    val available: Int,
    val collectionURI: String,
    val returned: Int,
    val items: List<ComicsItems>
)

data class ComicsItems(
    override val resourceURI: String,
    val name: String
) : MediaItem

data class ComicDetails(
    val id: Int,
    val title: String,
    val description: String?,
    val thumbnail: Thumbnail?
)