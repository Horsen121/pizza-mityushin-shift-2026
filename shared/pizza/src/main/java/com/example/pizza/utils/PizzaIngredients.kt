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
    PizzaIngredients.PINEAPPLE      -> R.string.shared_pizza_ingredient_pineapple
    PizzaIngredients.MOZZARELLA     -> R.string.shared_pizza_ingredient_mozzarella
    PizzaIngredients.PEPERONI       -> R.string.shared_pizza_ingredient_peperoni
    PizzaIngredients.GREEN_PEPPER   -> R.string.shared_pizza_ingredient_green_pepper
    PizzaIngredients.MUSHROOMS      -> R.string.shared_pizza_ingredient_mushrooms
    PizzaIngredients.BASIL          -> R.string.shared_pizza_ingredient_basil
    PizzaIngredients.CHEDDAR        -> R.string.shared_pizza_ingredient_cheddar
    PizzaIngredients.PARMESAN       -> R.string.shared_pizza_ingredient_parmesan
    PizzaIngredients.FETA           -> R.string.shared_pizza_ingredient_feta
    PizzaIngredients.HAM            -> R.string.shared_pizza_ingredient_ham
    PizzaIngredients.PICKLE         -> R.string.shared_pizza_ingredient_pickle
    PizzaIngredients.TOMATO         -> R.string.shared_pizza_ingredient_tomato
    PizzaIngredients.BACON          -> R.string.shared_pizza_ingredient_bacon
    PizzaIngredients.ONION          -> R.string.shared_pizza_ingredient_onion
    PizzaIngredients.CHILE          -> R.string.shared_pizza_ingredient_chile
    PizzaIngredients.SHRIMPS        -> R.string.shared_pizza_ingredient_shrimps
    PizzaIngredients.CHICKEN_FILLET -> R.string.shared_pizza_ingredient_chicken_fillet
    PizzaIngredients.MEATBALL       -> R.string.shared_pizza_ingredient_meatball
}
