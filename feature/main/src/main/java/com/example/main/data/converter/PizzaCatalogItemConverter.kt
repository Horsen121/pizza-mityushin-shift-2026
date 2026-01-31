package com.example.main.data.converter

import com.example.main.domain.entity.PizzaCatalogItem
import com.example.pizza.model.network.PizzaItemModel

class PizzaCatalogItemConverter {
    fun convert(model: PizzaItemModel): PizzaCatalogItem {
        val price = listOfNotNull(
            model.ingredients.minByOrNull { it.price }?.price,
                    model.toppings.minByOrNull { it.price }?.price,
                    model.sizes.minByOrNull { it.price }?.price,
                    model.doughs.minByOrNull { it.price }?.price
        ).sum()

        return PizzaCatalogItem(
            id = model.id.toLong(),
            name = model.name,
            description = model.description,
            minPrice = price,
            isNew = model.isNew,
            isHit = model.isHit,
            img = model.img,
        )
    }
}