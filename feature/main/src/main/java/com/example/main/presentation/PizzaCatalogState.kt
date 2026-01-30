package com.example.main.presentation

import com.example.main.domain.entity.PizzaCatalogItem

sealed interface PizzaCatalogState {
    data object Initial : PizzaCatalogState
    data object Loading : PizzaCatalogState
    data class Error(val message: String) : PizzaCatalogState
    data class Content(val pizzaCatalog: List<PizzaCatalogItem>) : PizzaCatalogState
}