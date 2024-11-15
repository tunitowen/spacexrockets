package dev.tonyowen.spacex.network.services

import dev.tonyowen.spacex.modules.network.rocket.RocketDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RocketService {

    @GET("rockets")
    suspend fun getRockets(): Response<List<RocketDto>>

    @GET("rockets/{id}")
    suspend fun getRocket(@Path("id") id: String): Response<RocketDto>
}