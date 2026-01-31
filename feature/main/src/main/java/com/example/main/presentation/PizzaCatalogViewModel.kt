package com.example.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.main.domain.usecase.GetPizzaCatalogUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PizzaCatalogViewModel(
    private val getPizzaCatalogUseCase: GetPizzaCatalogUseCase
): ViewModel() {

    private val _state = MutableStateFlow<PizzaCatalogState>(PizzaCatalogState.Initial)
    val state: StateFlow<PizzaCatalogState> = _state.asStateFlow()

    fun loadData() {
        if(_state.value is PizzaCatalogState.Loading || _state.value is PizzaCatalogState.Content)
            return

        _state.value = PizzaCatalogState.Loading
        viewModelScope.launch {
            try {
                val pizzaCatalog = getPizzaCatalogUseCase()
                _state.value = PizzaCatalogState.Content(pizzaCatalog)
            } catch (e: Exception) {
                _state.value = PizzaCatalogState.Error(e.message.orEmpty())
            }
        }
    }
}