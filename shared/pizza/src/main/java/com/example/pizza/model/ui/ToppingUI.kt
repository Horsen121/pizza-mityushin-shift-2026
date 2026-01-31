package com.example.pizza.model.ui

import com.example.pizza.model.network.Topping
import com.example.pizza.utils.PizzaIngredients
import com.example.pizza.utils.toIngredientTypeOrNull
import kotlinx.serialization.Serializable

@Serializable
data class ToppingUI(
    val type: PizzaIngredients,
    val price: Long,
    val img: String
)

fun ArrayList<Topping>.toDomain(): ArrayList<ToppingUI> =
    this.mapNotNull { it.toDomain() } as ArrayList<ToppingUI>

fun Topping.toDomain(): ToppingUI? {
    val ingredientType = type.toIngredientTypeOrNull() ?: return null
    return ToppingUI(
        type = ingredientType,
        price = price,
        img = img
    )
}