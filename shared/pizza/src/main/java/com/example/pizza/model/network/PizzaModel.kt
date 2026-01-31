package com.example.pizza.model.network

import kotlinx.serialization.Serializable

@Serializable
data class PizzaModel(
    var id: String,
    var name: String,
    var description: String,
    var ingredients: ArrayList<IngredientModel> = arrayListOf(),
    var toppings: ArrayList<ToppingModel> = arrayListOf(),
    var sizes: ArrayList<SizeModel> = arrayListOf(),
    var doughs: ArrayList<DoughModel> = arrayListOf(),
    var calories: Int,
    var protein: String,
    var totalFat: String,
    var carbohydrates: String,
    var sodium: String,
    var allergens: ArrayList<String> = arrayListOf(),
    var isVegetarian: Boolean,
    var isGlutenFree: Boolean,
    var isNew: Boolean,
    var isHit: Boolean,
    var img: String
)