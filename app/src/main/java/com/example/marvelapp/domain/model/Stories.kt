package com.example.marvelapp.domain.model

data class Stories(
    val available: Int,
    val collectionURI: String,
    val returned: Int,
    val items: List<StoriesItems>
)

data class StoriesItems(
    val resourceURI: String,
    val name: String,
    val type: String
)
