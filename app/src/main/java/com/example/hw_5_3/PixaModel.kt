package com.example.hw_5_3

data class PixaModel(
    val hits: ArrayList<ImageModel>
)

data class ImageModel(
    val largeImageURL: String,
    val likes: Int
)