package dev.tonyowen.spacex.modules.network.rocket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketDto(
    val height: Height? = null,
    val diameter: Diameter? = null,
    val mass: Mass? = null,
    @SerialName("first_stage") val firstStage: FirstStage? = null,
    @SerialName("second_stage") val secondStage: SecondStage? = null,
    val engines: Engines? = null,
    @SerialName("landing_legs") val landingLegs: LandingLegs? = null,
    @SerialName("payload_weights") val payloadWeights: List<PayloadWeight>? = null,
    @SerialName("flickr_images") val flickrImages: List<String>? = null,
    val name: String = "",
    val type: String = "",
    val active: Boolean = false,
    val stages: Int = 0,
    val boosters: Int = 0,
    @SerialName("cost_per_launch") val costPerLaunch: Int = 0,
    @SerialName("success_rate_pct") val successRatePct: Int = 0,
    @SerialName("first_flight") val firstFlight: String? = null,
    val country: String? = null,
    val company: String? = null,
    val wikipedia: String? = null,
    val description: String? = null,
    val id: String = ""
)

@Serializable
data class Height(
    val meters: Double? = null,
    val feet: Double? = null,
)

@Serializable
data class Diameter(
    val meters: Double? = null,
    val feet: Double? = null,
)

@Serializable
data class Mass(
    val kg: Int? = null,
    val lb: Int? = null,
)

@Serializable
data class FirstStage(
    @SerialName("thrust_sea_level") val thrustSeaLevel: Thrust? = null,
    @SerialName("thrust_vacuum") val thrustVacuum: Thrust? = null,
    val reusable: Boolean = false,
    val engines: Int? = null,
    @SerialName("fuel_amount_tons") val fuelAmountTons: Double? = null,
    @SerialName("burn_time_sec") val burnTimeSec: Int? = null,
)

@Serializable
data class Thrust(
    val kN: Int? = null,
    val lbf: Int? = null,
)

@Serializable
data class SecondStage(
    val thrust: Thrust? = null,
    val payloads: Payloads? = null,
    val reusable: Boolean = false,
    val engines: Int = 0,
    @SerialName("fuel_amount_tons") val fuelAmountTons: Double? = null,
    @SerialName("burn_time_sec") val burnTimeSec: Int? = null,
)

@Serializable
data class Payloads(
    @SerialName("composite_fairing") val compositeFairing: CompositeFairing? = null,
    @SerialName("option_1") val option1: String? = null,
)

@Serializable
data class CompositeFairing(
    val height: Height? = null,
    val diameter: Diameter? = null,
)

@Serializable
data class Engines(
    val isp: Isp? = null,
    @SerialName("thrust_sea_level") val thrustSeaLevel: Thrust? = null,
    @SerialName("thrust_vacuum") val thrustVacuum: Thrust? = null,
    val number: Int? = null,
    val type: String? = null,
    val version: String? = null,
    val layout: String? = null,
    @SerialName("engine_loss_max") val engineLossMax: Int? = null,
    @SerialName("propellant_1") val propellant1: String? = null,
    @SerialName("propellant_2") val propellant2: String? = null,
    @SerialName("thrust_to_weight") val thrustToWeight: Double? = null,
)

@Serializable
data class Isp(
    @SerialName("sea_level") val seaLevel: Int,
    val vacuum: Int? = null,
)

@Serializable
data class LandingLegs(
    val number: Int = 0,
    val material: String? = null
)

@Serializable
data class PayloadWeight(
    val id: String = "",
    val name: String = "",
    val kg: Int? = null,
    val lb: Int? = null
)
