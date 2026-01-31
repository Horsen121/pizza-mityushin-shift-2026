package com.example.pizza.utils

import com.example.pizza.R

enum class PizzaSizes {
    SMALL,
    MEDIUM,
    LARGE
}

fun ArrayList<String>.toSizeType(): ArrayList<PizzaSizes> =
    this.mapNotNull { el ->
        runCatching { PizzaSizes.valueOf(el) }.getOrNull()
    } as ArrayList<PizzaSizes>


fun String.toSizeTypeOrNull(): PizzaSizes? =
    runCatching { PizzaSizes.valueOf(this) }.getOrNull()

fun PizzaSizes.toNameRes(): Int = when (this) {
    PizzaSizes.SMALL -> R.string.shared_pizza_size_small
    PizzaSizes.MEDIUM -> R.string.shared_pizza_size_medium
    PizzaSizes.LARGE -> R.string.shared_pizza_size_large
}