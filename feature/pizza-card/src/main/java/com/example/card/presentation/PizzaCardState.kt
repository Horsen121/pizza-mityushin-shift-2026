package com.example.card.presentation

import com.example.card.domain.entity.PizzaCardItem

sealed interface PizzaCardState {
    data object Initial : PizzaCardState
    data object Loading : PizzaCardState
    data class Error(val message: String) : PizzaCardState
    data class Content(val pizza: PizzaCardItem) : PizzaCardState
}