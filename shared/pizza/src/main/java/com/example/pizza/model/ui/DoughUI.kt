package com.example.pizza.model.ui

import com.example.pizza.model.network.DoughModel
import com.example.pizza.utils.PizzaDoughs
import com.example.pizza.utils.toDoughTypeOrNull
import kotlinx.serialization.Serializable

@Serializable
data class DoughUI(
    val type: PizzaDoughs,
    val price: Long,
)


fun ArrayList<DoughModel>.toDomain(): ArrayList<DoughUI> = this
    .mapNotNull { it.toDomain() } as ArrayList<DoughUI>

fun DoughModel.toDomain(): DoughUI? {
    val doughType = type.toDoughTypeOrNull() ?: return null
    return DoughUI(
        type = doughType,
        price = price
    )
}