package dev.tonyowen.spacex.network.services

import dev.tonyowen.spacex.modules.network.rocket.Rocket
import retrofit2.Response
import retrofit2.http.GET

interface RocketService {

    @GET
    suspend fun getRockets(): Response<List<Rocket>>

    @GET
    suspend fun getRocket(id: String): Response<Rocket>
}