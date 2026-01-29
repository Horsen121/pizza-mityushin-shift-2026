package com.example.main.data.converter

import com.example.main.data.PizzaCatalogItemModel
import com.example.main.domain.entity.PizzaCatalogItem

class PizzaCatalogItemConverter {
    fun convert(model: PizzaCatalogItemModel): PizzaCatalogItem =
        PizzaCatalogItem(
            id = model.id,
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