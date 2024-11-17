package com.example.marvelapp.domain.model

import com.example.marvelapp.presentation.components.details.generics.MediaItem

data class Events(
    val available: String,
    val collectionURI: String,
    val items: List<EventsItems>
)

data class EventsItems(
    override val resourceURI: String,
    val name: String
) : MediaItem