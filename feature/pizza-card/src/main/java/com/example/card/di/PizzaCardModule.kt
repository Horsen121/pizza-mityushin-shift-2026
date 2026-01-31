package com.example.card.di

import com.example.card.data.PizzaCardApi
import com.example.card.data.converter.PizzaCardItemConverter
import com.example.card.data.repository.PizzaCardRepositoryImpl
import com.example.card.domain.entity.PizzaCardItem
import com.example.card.domain.repository.PizzaCardRepository
import com.example.card.domain.usecase.GetPizzaCardUseCase
import com.example.card.presentation.PizzaCardViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit

val pizzaCardModule = module {

    single { get<Retrofit>().create(PizzaCardApi::class.java) }
    factoryOf(::PizzaCardRepositoryImpl) { bind<PizzaCardRepository>() }
    factoryOf(::PizzaCardItem)
    factoryOf(::GetPizzaCardUseCase)
    factoryOf(::PizzaCardItemConverter)
    viewModelOf(::PizzaCardViewModel)
}