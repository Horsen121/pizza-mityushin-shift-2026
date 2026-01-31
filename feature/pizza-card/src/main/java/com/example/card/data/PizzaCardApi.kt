package com.example.card.data

import com.example.card.data.repository.PizzaCardResponse
import retrofit2.Response
import retrofit2.http.GET

interface PizzaCardApi {

    @GET("pizza/catalog")
    suspend fun get(): Response<PizzaCardResponse>
}