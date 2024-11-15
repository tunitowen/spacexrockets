package dev.tonyowen.spacex.di

import dev.tonyowen.spacex.core.constants.NetworkConstants
import dev.tonyowen.spacex.network.repos.RocketRepository
import dev.tonyowen.spacex.network.services.RocketService
import dev.tonyowen.spacex.screens.details.DetailsScreenViewModel
import dev.tonyowen.spacex.screens.home.HomeScreenViewModel
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val appModule = module {
    single { OkHttpClient.Builder().build() }
    single {
        val networkJson = Json { ignoreUnknownKeys = true }

        Retrofit.Builder()
            .baseUrl(NetworkConstants.baseUrl)
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .client(get())
            .build()
    }

    single<RocketService> {
        get<Retrofit>().create(RocketService::class.java)
    }

    single { RocketRepository(get()) }

    viewModel { HomeScreenViewModel(get()) }
    viewModel { DetailsScreenViewModel(get()) }
}