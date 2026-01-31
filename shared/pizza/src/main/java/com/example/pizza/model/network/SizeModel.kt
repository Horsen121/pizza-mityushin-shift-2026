package com.example.pizza.model.network

import kotlinx.serialization.Serializable

@Serializable
data class SizeModel(
    val type: String,
    val price: Long,
)
