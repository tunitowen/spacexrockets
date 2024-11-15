package dev.tonyowen.spacex.network.mappers

import dev.tonyowen.spacex.modules.business.rocket.Rocket
import dev.tonyowen.spacex.modules.network.rocket.RocketDto

fun RocketDto.toRocket(): Rocket = Rocket(
    id = this.id,
    name = this.name,
    description = this.description.orEmpty(),
    company = this.company.orEmpty(),
    country = this.country.orEmpty(),
    firstFlight = this.firstFlight.orEmpty(),
    costPerLaunch = this.costPerLaunch,
    successRate = this.successRatePct,



    type = this.type,
    active = this.active,
    images = this.flickrImages.orEmpty()
)