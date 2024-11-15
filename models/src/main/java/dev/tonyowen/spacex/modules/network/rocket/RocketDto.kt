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
    val meters: Double,
    val feet: Double
)

@Serializable
data class Diameter(
    val meters: Double,
    val feet: Double
)

@Serializable
data class Mass(
    val kg: Int,
    val lb: Int
)

@Serializable
data class FirstStage(
    @SerialName("thrust_sea_level") val thrustSeaLevel: Thrust,
    @SerialName("thrust_vacuum") val thrustVacuum: Thrust,
    val reusable: Boolean,
    val engines: Int,
    @SerialName("fuel_amount_tons") val fuelAmountTons: Int,
    @SerialName("burn_time_sec") val burnTimeSec: Int
)

@Serializable
data class Thrust(
    val kN: Int,
    val lbf: Int
)

@Serializable
data class SecondStage(
    val thrust: Thrust,
    val payloads: Payloads,
    val reusable: Boolean,
    val engines: Int,
    @SerialName("fuel_amount_tons") val fuelAmountTons: Int,
    @SerialName("burn_time_sec") val burnTimeSec: Int
)

@Serializable
data class Payloads(
    @SerialName("composite_fairing") val compositeFairing: CompositeFairing,
    @SerialName("option_1") val option1: String
)

@Serializable
data class CompositeFairing(
    val height: Height,
    val diameter: Diameter
)

@Serializable
data class Engines(
    val isp: Isp,
    @SerialName("thrust_sea_level") val thrustSeaLevel: Thrust,
    @SerialName("thrust_vacuum") val thrustVacuum: Thrust,
    val number: Int,
    val type: String,
    val version: String,
    val layout: String,
    @SerialName("engine_loss_max") val engineLossMax: Int,
    @SerialName("propellant_1") val propellant1: String,
    @SerialName("propellant_2") val propellant2: String,
    @SerialName("thrust_to_weight") val thrustToWeight: Double
)

@Serializable
data class Isp(
    @SerialName("sea_level") val seaLevel: Int,
    val vacuum: Int
)

@Serializable
data class LandingLegs(
    val number: Int,
    val material: String
)

@Serializable
data class PayloadWeight(
    val id: String,
    val name: String,
    val kg: Int,
    val lb: Int
)
