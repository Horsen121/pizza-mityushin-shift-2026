package com.example.main.data

import com.example.pizza.model.Dough
import com.example.pizza.model.Ingredient
import com.example.pizza.model.Size
import com.example.pizza.model.Topping
import kotlinx.serialization.Serializable

@Serializable
data class PizzaCatalogItemModel(
    var id: String? = null,
    var name: String? = null,
    var ingredients: ArrayList<Ingredient> = arrayListOf(),
    var toppings: ArrayList<Topping> = arrayListOf(),
    var description: String? = null,
    var sizes: ArrayList<Size> = arrayListOf(),
    var doughs: ArrayList<Dough> = arrayListOf(),
    var calories: Int? = null,
    var protein: String? = null,
    var totalFat: String? = null,
    var carbohydrates: String? = null,
    var sodium: String? = null,
    var allergens: ArrayList<String> = arrayListOf(),
    var isVegetarian: Boolean? = null,
    var isGlutenFree: Boolean? = null,
    var isNew: Boolean? = null,
    var isHit: Boolean? = null,
    var img: String? = null
)
