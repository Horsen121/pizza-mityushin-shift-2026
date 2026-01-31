package com.example.card.data.converter

import com.example.card.domain.entity.PizzaCardItem
import com.example.pizza.model.PizzaItemModel

class PizzaCardItemConverter {
    fun convert(model: PizzaItemModel): PizzaCardItem {
        return PizzaCardItem(
            id = model.id.toLong(),
            name = model.name,
            description = model.description,
            ingredients = model.ingredients,
            toppings = model.toppings,
            sizes = model.sizes,
            doughs = model.doughs,
            isNew = model.isNew,
            isHit = model.isHit,
            img = model.img,
        )
    }
}