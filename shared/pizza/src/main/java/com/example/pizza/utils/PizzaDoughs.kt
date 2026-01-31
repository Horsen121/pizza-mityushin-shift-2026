package com.example.pizza.utils

import com.example.pizza.R

enum class PizzaDoughs {
    THIN,
    THICK
}

fun ArrayList<String>.toDoughType(): ArrayList<PizzaDoughs> =
    this.mapNotNull { el ->
        runCatching { PizzaDoughs.valueOf(el) }.getOrNull()
    } as ArrayList<PizzaDoughs>


fun String.toDoughTypeOrNull(): PizzaDoughs? =
    runCatching { PizzaDoughs.valueOf(this) }.getOrNull()

fun PizzaDoughs.toNameRes(): Int = when (this) {
    PizzaDoughs.THIN -> R.string.shared_pizza_dough_thin
    PizzaDoughs.THICK -> R.string.shared_pizza_dough_thick
}