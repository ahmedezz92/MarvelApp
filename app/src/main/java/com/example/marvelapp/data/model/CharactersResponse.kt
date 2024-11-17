package com.example.marvelapp.data.model

import com.example.marvelapp.domain.model.Character

data class CharactersResponse(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Character>
)