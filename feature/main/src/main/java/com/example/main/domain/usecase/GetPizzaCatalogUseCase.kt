package com.example.main.domain.usecase

import com.example.main.domain.entity.PizzaCatalogItem
import com.example.main.domain.repository.PizzaCatalogRepository

class GetPizzaCatalogUseCase(
    private val repository: PizzaCatalogRepository
) {
    suspend operator fun invoke(): List<PizzaCatalogItem> = repository.get()
}