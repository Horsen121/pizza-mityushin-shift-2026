package com.example.main.di

import com.example.main.data.PizzaCatalogApi
import com.example.main.data.converter.PizzaCatalogItemConverter
import com.example.main.data.repository.PizzaCatalogRepositoryImpl
import com.example.main.domain.entity.PizzaCatalogItem
import com.example.main.domain.repository.PizzaCatalogRepository
import com.example.main.domain.usecase.GetPizzaCatalogUseCase
import com.example.main.presentation.PizzaCatalogScreenViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit

val pizzaCatalogModule = module {
    single { get<Retrofit>().create(PizzaCatalogApi::class.java) }
    singleOf(::PizzaCatalogRepositoryImpl) { bind<PizzaCatalogRepository>() }
    factoryOf(::PizzaCatalogItem)
    factoryOf(::GetPizzaCatalogUseCase)
    factoryOf(::PizzaCatalogItemConverter)
    viewModelOf(::PizzaCatalogScreenViewModel)
}