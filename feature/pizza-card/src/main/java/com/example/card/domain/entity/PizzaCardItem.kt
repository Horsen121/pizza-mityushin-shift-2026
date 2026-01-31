package com.example.card.domain.entity

import com.example.pizza.model.Dough
import com.example.pizza.model.Ingredient
import com.example.pizza.model.Size
import com.example.pizza.model.Topping

data class PizzaCardItem(
    var id: Long,
    var name: String,
    var description: String,
    var ingredients: ArrayList<Ingredient>,
    var toppings: ArrayList<Topping>,
    var sizes: ArrayList<Size>,
    var doughs: ArrayList<Dough>,
    var isNew: Boolean,
    var isHit: Boolean,
    var img: String
)
