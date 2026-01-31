package com.example.card.domain.usecase

import com.example.card.domain.repository.PizzaCardRepository

class GetPizzaCardUseCase(
    private val repository: PizzaCardRepository
) {
    suspend operator fun invoke(id: Long) = repository.get(id)
}