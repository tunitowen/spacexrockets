package dev.tonyowen.spacex.network.repos

import dev.tonyowen.spacex.modules.network.rocket.Rocket
import dev.tonyowen.spacex.network.services.RocketService
import dev.tonyowen.spacex.network.utils.NetworkResponse
import dev.tonyowen.spacex.network.utils.foldIntoNetworkResponse

class RocketRepository(private val service: RocketService) {

    suspend fun getRockets(): NetworkResponse<List<Rocket>> {
        return service.getRockets().foldIntoNetworkResponse()
    }

    suspend fun getRocketById(id: String): NetworkResponse<Rocket> {
        return service.getRocket(id).foldIntoNetworkResponse()
    }
}