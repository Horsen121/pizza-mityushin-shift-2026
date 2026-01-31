package com.example.card.data.converter

import com.example.card.domain.entity.PizzaCardItem
import com.example.pizza.model.network.PizzaModel
import com.example.pizza.model.ui.toDomain

class PizzaCardItemConverter {

    fun convert(model: PizzaModel): PizzaCardItem {
        return PizzaCardItem(
            id = model.id.toLong(),
            name = model.name,
            description = model.description,
            ingredients = model.ingredients.toDomain(),
            toppings = model.toppings.toDomain(),
            sizes = model.sizes.toDomain(),
            doughs = model.doughs.toDomain(),
            isNew = model.isNew,
            isHit = model.isHit,
            img = model.img,
        )
    }
}