package com.example.main.data.repository

import com.example.main.data.PizzaCatalogApi
import com.example.main.data.converter.PizzaCatalogItemConverter
import com.example.main.domain.entity.PizzaCatalogItem
import com.example.main.domain.repository.PizzaCatalogRepository

class PizzaCatalogRepositoryImpl(
    private val dataSource: PizzaCatalogApi,
    private val converter: PizzaCatalogItemConverter
): PizzaCatalogRepository {
    override suspend fun get(): List<PizzaCatalogItem> {
        return dataSource.get().map { converter.convert(it) }
    }
}