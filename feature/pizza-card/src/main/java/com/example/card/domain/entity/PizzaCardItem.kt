package com.example.card.domain.entity

import com.example.pizza.model.ui.DoughUI
import com.example.pizza.model.ui.IngredientUI
import com.example.pizza.model.ui.SizeUI
import com.example.pizza.model.ui.ToppingUI

data class PizzaCardItem(
    var id: Long,
    var name: String,
    var description: String,
    var ingredients: ArrayList<IngredientUI>,
    var toppings: ArrayList<ToppingUI>,
    var sizes: ArrayList<SizeUI>,
    var doughs: ArrayList<DoughUI>,
    var isNew: Boolean,
    var isHit: Boolean,
    var img: String
)
