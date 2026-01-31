package com.example.pizza_mityushin_shift_2026.app

import com.example.basket.BasketRoute
import com.example.main.PizzaCatalogRoute
import com.example.orders.OrdersRoute
import com.example.profile.ProfileRoute
import kotlin.reflect.KClass

enum class NavigationOption(val route: KClass<*>) {
    MAIN(PizzaCatalogRoute::class),
    BASKET(BasketRoute::class),
    ORDERS(OrdersRoute::class),
    PROFILE(ProfileRoute::class)
}