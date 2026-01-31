package com.example.card.domain.repository

import com.example.card.domain.entity.PizzaCardItem

interface PizzaCardRepository {
    suspend fun get(id: Long): PizzaCardItem?
}