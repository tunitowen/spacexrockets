package dev.tonyowen.spacex.modules.business.rocket

data class Rocket(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val company: String = "",
    val country: String = "",
    val firstFlight: String = "",
    val costPerLaunch: Int = 0,
    val successRate: Int = 0,


    val type: String = "",
    val active: Boolean = true,
    val images: List<String> = emptyList()
)
