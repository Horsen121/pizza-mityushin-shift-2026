package com.example.pizza_mityushin_shift_2026.app

import kotlin.reflect.KClass

enum class NavigationOption(val route: KClass<*>) {
    // NavBar
    MAIN(MainRoute::class),
    BASKET(BasketRoute::class),
    ORDERS(OrdersRoute::class),
    PROFILE(ProfileRoute::class),

    CARD(CardRoute::class),
}