package com.example.pizza.model

import kotlinx.serialization.Serializable

@Serializable
data class Dough(
    val type: String,
    val price: Long,
)
