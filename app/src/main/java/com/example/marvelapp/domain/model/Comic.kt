package com.example.marvelapp.domain.model

data class Comic(
    val available:Int,
    val collectionURI:String,
    val returned:Int,
    val items:List<ComicsItems>
)
data class ComicsItems(
    val resourceURI:String,
    val name:String
)