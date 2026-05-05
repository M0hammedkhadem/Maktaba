package com.ElOuedUniv.maktaba.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val isbn: String,
    val title: String,
    @SerialName("nbpages")
    val nbPages: Int,
    @SerialName("imageurl")
    val imageUrl: String? = null
)
