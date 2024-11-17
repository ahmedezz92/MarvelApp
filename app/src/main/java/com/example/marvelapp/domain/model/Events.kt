package com.example.marvelapp.domain.model

data class Events(
    val available: String,
    val collectionURI: String,
    val items: List<EventsItems>
)

data class EventsItems(
    val resourceURI: String,
    val name: String
)