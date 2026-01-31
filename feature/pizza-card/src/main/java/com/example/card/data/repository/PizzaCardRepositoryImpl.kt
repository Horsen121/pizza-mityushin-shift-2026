package com.example.card.data.repository

import com.example.card.data.PizzaCardApi
import com.example.card.data.converter.PizzaCardItemConverter
import com.example.card.domain.entity.PizzaCardItem
import com.example.card.domain.repository.PizzaCardRepository

class PizzaCardRepositoryImpl(
    private val dataSource: PizzaCardApi,
    private val converter: PizzaCardItemConverter
): PizzaCardRepository {
    override suspend fun get(id: Long): PizzaCardItem? {
        val item = dataSource.get().body()?.catalog?.firstOrNull { it.id.toLong() == id }
        return if (item != null) converter.convert(item) else null
    }
}