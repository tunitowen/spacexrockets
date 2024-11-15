package dev.tonyowen.spacex.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeDestination

@Serializable
data class DetailsDestination(
    val id: String
)