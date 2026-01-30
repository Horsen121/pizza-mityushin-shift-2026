package com.example.main.domain.repository

import com.example.main.domain.entity.PizzaCatalogItem

interface PizzaCatalogRepository {
    suspend fun get(): List<PizzaCatalogItem>
}