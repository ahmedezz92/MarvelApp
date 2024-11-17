package com.example.marvelapp.domain.model

data class Series(
    val available: Int,
    val collectionURI: String,
    val returned: Int,
    val items: List<SeriesItems>
)

data class SeriesItems(
    val resourceURI: String,
    val name: String
)