package dev.tonyowen.spacex.network.mappers

import dev.tonyowen.spacex.modules.business.rocket.Rocket
import dev.tonyowen.spacex.modules.network.rocket.RocketDto

fun RocketDto.toRocket(): Rocket = Rocket(
    id = this.id,
    name = this.name,
    type = this.type,
    active = this.active,
    images = this.flickrImages.orEmpty()
)