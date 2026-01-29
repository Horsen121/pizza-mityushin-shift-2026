package com.example.main.domain.entity

data class PizzaCatalogItem(
    var id: Long,
    var name: String,
    var description: String,
    val minPrice: Long,
    var isNew: Boolean? = null,
    var isHit: Boolean? = null,
    var img: String? = null
)
