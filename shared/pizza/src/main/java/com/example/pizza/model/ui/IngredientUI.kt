package com.example.pizza.model.ui

import com.example.pizza.model.network.IngredientModel
import com.example.pizza.utils.PizzaIngredients
import com.example.pizza.utils.toIngredientTypeOrNull
import kotlinx.serialization.Serializable

@Serializable
data class IngredientUI(
    val type: PizzaIngredients,
    val price: Long,
    val img: String
)

fun ArrayList<IngredientModel>.toDomain(): ArrayList<IngredientUI> = this
    .mapNotNull { it.toDomain() } as ArrayList<IngredientUI>

fun IngredientModel.toDomain(): IngredientUI? {
    val ingredientType = type.toIngredientTypeOrNull() ?: return null
    return IngredientUI(
        type = ingredientType,
        price = price,
        img = img
    )
}