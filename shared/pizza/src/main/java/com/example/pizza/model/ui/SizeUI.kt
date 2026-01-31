package com.example.pizza.model.ui

import com.example.pizza.model.network.Size
import com.example.pizza.utils.PizzaSizes
import com.example.pizza.utils.toSizeTypeOrNull
import kotlinx.serialization.Serializable

@Serializable
data class SizeUI(
    val type: PizzaSizes,
    val price: Long,
)


fun ArrayList<Size>.toDomain(): ArrayList<SizeUI> = this
    .mapNotNull { it.toDomain() } as ArrayList<SizeUI>

fun Size.toDomain(): SizeUI? {
    val sizeType = type.toSizeTypeOrNull() ?: return null
    return SizeUI(
        type = sizeType,
        price = price
    )
}