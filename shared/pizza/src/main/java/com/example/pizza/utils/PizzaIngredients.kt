package com.example.pizza.utils

import com.example.pizza.R

enum class PizzaIngredients {
    PINEAPPLE,
    MOZZARELLA,
    PEPERONI,
    GREEN_PEPPER,
    MUSHROOMS,
    BASIL,
    CHEDDAR,
    PARMESAN,
    FETA,
    HAM,
    PICKLE,
    TOMATO,
    BACON,
    ONION,
    CHILE,
    SHRIMPS,
    CHICKEN_FILLET,
    MEATBALL
}

fun ArrayList<String>.toIngredientType(): ArrayList<PizzaIngredients> =
    this.mapNotNull { el ->
        runCatching { PizzaIngredients.valueOf(el) }.getOrNull()
    } as ArrayList<PizzaIngredients>


fun String.toIngredientTypeOrNull(): PizzaIngredients? =
    runCatching { PizzaIngredients.valueOf(this) }.getOrNull()

fun PizzaIngredients.toNameRes(): Int = when (this) {
    PizzaIngredients.PINEAPPLE      -> R.string.ingredient_pineapple
    PizzaIngredients.MOZZARELLA     -> R.string.ingredient_mozzarella
    PizzaIngredients.PEPERONI       -> R.string.ingredient_peperoni
    PizzaIngredients.GREEN_PEPPER   -> R.string.ingredient_green_pepper
    PizzaIngredients.MUSHROOMS      -> R.string.ingredient_mushrooms
    PizzaIngredients.BASIL          -> R.string.ingredient_basil
    PizzaIngredients.CHEDDAR        -> R.string.ingredient_cheddar
    PizzaIngredients.PARMESAN       -> R.string.ingredient_parmesan
    PizzaIngredients.FETA           -> R.string.ingredient_feta
    PizzaIngredients.HAM            -> R.string.ingredient_ham
    PizzaIngredients.PICKLE         -> R.string.ingredient_pickle
    PizzaIngredients.TOMATO         -> R.string.ingredient_tomato
    PizzaIngredients.BACON          -> R.string.ingredient_bacon
    PizzaIngredients.ONION          -> R.string.ingredient_onion
    PizzaIngredients.CHILE          -> R.string.ingredient_chile
    PizzaIngredients.SHRIMPS        -> R.string.ingredient_shrimps
    PizzaIngredients.CHICKEN_FILLET -> R.string.ingredient_chicken_fillet
    PizzaIngredients.MEATBALL       -> R.string.ingredient_meatball
}
