package com.example.pizza.model

import kotlinx.serialization.Serializable

@Serializable
data class PizzaItemModel(
    var id: String,
    var name: String,
    var description: String,
    var ingredients: ArrayList<Ingredient> = arrayListOf(),
    var toppings: ArrayList<Topping> = arrayListOf(),
    var sizes: ArrayList<Size> = arrayListOf(),
    var doughs: ArrayList<Dough> = arrayListOf(),
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