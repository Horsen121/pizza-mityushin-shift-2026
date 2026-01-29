package com.example.main.domain.entity

import com.example.pizza.model.Dough
import com.example.pizza.model.Ingredient
import com.example.pizza.model.Size
import com.example.pizza.model.Topping

data class PizzaCatalogItem(
    var id: String? = null,
    var name: String? = null,
    var description: String? = null,
    var ingredients: ArrayList<Ingredient> = arrayListOf(),
    var toppings: ArrayList<Topping> = arrayListOf(),
    var sizes: ArrayList<Size> = arrayListOf(),
    var doughs: ArrayList<Dough> = arrayListOf(),
    var isNew: Boolean? = null,
    var isHit: Boolean? = null,
    var img: String? = null
)
