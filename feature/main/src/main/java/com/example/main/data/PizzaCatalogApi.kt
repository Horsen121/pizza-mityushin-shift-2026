package com.example.main.data

import com.example.main.data.repository.PizzaCatalogResponse
import retrofit2.Response
import retrofit2.http.GET

interface PizzaCatalogApi {
    @GET("pizza/catalog")
    suspend fun get(): Response<PizzaCatalogResponse>
}