package com.example.main.domain.usecase

import com.example.main.domain.entity.PizzaCatalogItem
import com.example.main.domain.repository.PizzaCatalogRepository

class GetPizzaCatalog(
    private val repository: PizzaCatalogRepository
) {
    suspend fun invoke(): List<PizzaCatalogItem> = repository.get()
}