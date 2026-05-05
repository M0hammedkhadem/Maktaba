package com.ElOuedUniv.maktaba.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Category(
    val id: String,
    val name: String,
    val description: String,
    @Transient val iconRes: Int = 0
)