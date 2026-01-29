package com.example.pizza.model

import kotlinx.serialization.Serializable

@Serializable
data class Size(
    val type: String,
    val price: Long,
)
