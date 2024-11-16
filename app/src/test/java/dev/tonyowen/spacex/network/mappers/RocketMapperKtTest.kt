package dev.tonyowen.spacex.network.mappers

import dev.tonyowen.spacex.core.extensions.formatNumber
import dev.tonyowen.spacex.core.extensions.orZero
import dev.tonyowen.spacex.modules.network.rocket.Diameter
import dev.tonyowen.spacex.modules.network.rocket.Engines
import dev.tonyowen.spacex.modules.network.rocket.FirstStage
import dev.tonyowen.spacex.modules.network.rocket.Height
import dev.tonyowen.spacex.modules.network.rocket.Mass
import dev.tonyowen.spacex.modules.network.rocket.RocketDto
import dev.tonyowen.spacex.modules.network.rocket.Thrust
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.NumberFormat

class RocketMapperKtTest {

    @Test
    fun rocketMapperMapsCorrectly() {

        val massInKg = 1000000
        val massInLb = 2000

        val rocketDto = RocketDto(
            id = "id",
            name = "name",
            description = "description",
            company = "company",
            country = "country",
            firstFlight = "firstFlight",
            costPerLaunch = 100,
            successRatePct = 80,
            height = Height(100.0, 200.0),
            diameter = Diameter(50.0, 60.0),
            mass = Mass(massInKg, massInLb),
            type = "engineType",
            engines = Engines(number = 2, propellant1 = "Unleaded"),
            firstStage = FirstStage(thrustSeaLevel = Thrust(100, 200), thrustVacuum = Thrust(300, 400)),
            wikipedia = "hello",
            flickrImages = listOf("1", "2")
        )

        val mapped = rocketDto.toRocket()

        assertEquals(mapped.id, rocketDto.id)
        assertEquals(mapped.name, rocketDto.name)
        assertEquals(mapped.description, rocketDto.description)
        assertEquals(mapped.company, rocketDto.company)
        assertEquals(mapped.country, rocketDto.country)
        assertEquals(mapped.firstFlight, rocketDto.firstFlight)
        assertEquals(mapped.costPerLaunch, rocketDto.costPerLaunch)
        assertEquals(mapped.successRate, rocketDto.successRatePct)

        assertEquals(mapped.height, "${rocketDto.height?.meters.orZero().formatNumber()}m (${rocketDto.height?.feet.orZero().formatNumber()}ft)")
        assertEquals(
            mapped.diameter,
            "${rocketDto.diameter?.meters.orZero().formatNumber()}m (${rocketDto.diameter?.feet.orZero().formatNumber()}ft)",
        )

        assertEquals(mapped.mass, "${NumberFormat.getInstance().format(massInKg)}kg (${massInLb.orZero().formatNumber()}lb)")
        assertEquals(mapped.numberOfEngines, rocketDto.engines?.number)
        assertEquals(
            mapped.thrust,
            "${rocketDto.firstStage?.thrustSeaLevel?.kN.orZero().formatNumber()}kN (${
                rocketDto.firstStage?.thrustSeaLevel?.lbf.orZero().formatNumber()
            }lbf) as sea level, ${
                rocketDto.firstStage?.thrustVacuum?.kN.orZero().formatNumber()
            }kN (${rocketDto.firstStage?.thrustVacuum?.lbf.orZero().formatNumber()}lbf) as vacuum"
        )
        assertEquals(mapped.fuel, rocketDto.engines?.propellant1)

        assertEquals(mapped.wikipediaLink, rocketDto.wikipedia)
        assertEquals(mapped.images.size, 2)
    }

}