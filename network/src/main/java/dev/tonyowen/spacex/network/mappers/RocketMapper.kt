package dev.tonyowen.spacex.network.mappers

import dev.tonyowen.spacex.core.extensions.formatNumber
import dev.tonyowen.spacex.core.extensions.orZero
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

    height = "${this.height?.meters.orZero().formatNumber()}m (${this.height?.feet.orZero().formatNumber()}ft)",
    diameter = "${this.diameter?.meters.orZero().formatNumber()}m (${this.diameter?.feet.orZero().formatNumber()}ft)",
    mass = "${this.mass?.kg.orZero().formatNumber()}kg (${this.mass?.lb.orZero().formatNumber()}lb)",


    engineType = this.type,
    numberOfEngines = this.engines?.number.orZero(),
    thrust = "${this.firstStage?.thrustSeaLevel?.kN.orZero().formatNumber()}kN (${this.firstStage?.thrustSeaLevel?.lbf.orZero().formatNumber()}lbf) as sea level, ${this.firstStage?.thrustVacuum?.kN.orZero().formatNumber()}kN (${this.firstStage?.thrustVacuum?.lbf.orZero().formatNumber()}lbf) as vacuum",
    fuel = this.engines?.propellant1.orEmpty(),

    wikipediaLink = this.wikipedia.orEmpty(),
    images = this.flickrImages.orEmpty()
)