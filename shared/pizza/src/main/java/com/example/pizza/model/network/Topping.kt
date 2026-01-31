package com.example.pizza.model.network

import kotlinx.serialization.Serializable

@Serializable
data class Topping(
    val type: String,
    val price: Long,
    val img: String
)
