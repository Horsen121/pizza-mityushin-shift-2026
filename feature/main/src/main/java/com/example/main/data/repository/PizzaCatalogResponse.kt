package com.example.main.data.repository

import com.example.main.data.PizzaCatalogItemModel
import kotlinx.serialization.Serializable

@Serializable
data class PizzaCatalogResponse(
    val success: Boolean,
    val reason: String? = null,
    val catalog: List<PizzaCatalogItemModel>
)