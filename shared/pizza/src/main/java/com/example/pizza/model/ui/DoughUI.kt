package com.example.pizza.model.ui

import com.example.pizza.model.network.Dough
import com.example.pizza.utils.PizzaDoughs
import com.example.pizza.utils.toDoughTypeOrNull
import kotlinx.serialization.Serializable

@Serializable
data class DoughUI(
    val type: PizzaDoughs,
    val price: Long,
)


fun ArrayList<Dough>.toDomain(): ArrayList<DoughUI> = this
    .mapNotNull { it.toDomain() } as ArrayList<DoughUI>

fun Dough.toDomain(): DoughUI? {
    val doughType = type.toDoughTypeOrNull() ?: return null
    return DoughUI(
        type = doughType,
        price = price
    )
}