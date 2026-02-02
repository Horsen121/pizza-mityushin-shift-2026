package com.example.card.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import com.example.card.domain.usecase.GetPizzaCardUseCase
import com.example.pizza.utils.PizzaDoughs
import com.example.pizza.utils.PizzaSizes
import com.example.pizza.utils.toNameRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.card.R as CurrentR

class PizzaCardViewModel(
    application: Application,
    private val getPizzaCardUseCase: GetPizzaCardUseCase,
    private val pizzaId: Long
): AndroidViewModel(application) {

    private val _state = MutableStateFlow<PizzaCardState>(PizzaCardState.Initial)
    val state: StateFlow<PizzaCardState> = _state.asStateFlow()

    fun loadData() {
        if(_state.value is PizzaCardState.Loading || _state.value is PizzaCardState.Content)
            return

        _state.value = PizzaCardState.Loading
        viewModelScope.launch {
            try {
                val pizza = getPizzaCardUseCase(pizzaId) ?: throw Exception()
                _state.value = PizzaCardState.Content(pizza)
            } catch (e: Exception) {
                _state.value = PizzaCardState.Error(e.message.orEmpty())
            }
        }
    }

    fun getIngredients(state: PizzaCardState): String {
        if(state !is PizzaCardState.Content)
            return ""

        var ingredients = ""
        state.pizza.ingredients.forEach {
            ingredients += "${application.getString(it.type.toNameRes())}, "
        }

        return ingredients.removeSuffix(", ")
    }

    fun onSizeSelect(size: Int) {

    }
}

fun PizzaSizes.toDescRes(): Int = when (this) {
    PizzaSizes.SMALL -> CurrentR.string.pizza_card_size_small
    PizzaSizes.MEDIUM -> CurrentR.string.pizza_card_size_medium
    PizzaSizes.LARGE -> CurrentR.string.pizza_card_size_large
}

fun PizzaDoughs.toDescRes(): Int = when (this) {
    PizzaDoughs.THIN -> CurrentR.string.pizza_card_dough_thin
    PizzaDoughs.THICK -> CurrentR.string.pizza_card_dough_thick
}