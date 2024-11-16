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

    val height: String = "",
    val diameter: String = "",
    val mass: String = "",

    val engineType: String = "",
    val numberOfEngines: Int = 0,
    val thrust: String = "",
    val fuel: String = "",

    val wikipediaLink: String = "",

    val active: Boolean = true,
    val images: List<String> = emptyList()
)
