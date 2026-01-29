package com.example.main.data

import retrofit2.http.GET

interface PizzaCatalogApi {
    @GET("pizza/catalog")
    suspend fun get(): List<PizzaCatalogItemModel>
}