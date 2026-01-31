package com.example.main.data.repository

import com.example.pizza.model.network.PizzaModel
import kotlinx.serialization.Serializable

@Serializable
data class PizzaCatalogResponse(
    val success: Boolean,
    val reason: String? = null,
    val catalog: List<PizzaModel>
)