package dev.tonyowen.spacex.di

import dev.tonyowen.spacex.network.repos.RocketRepository
import dev.tonyowen.spacex.network.services.RocketService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single { OkHttpClient.Builder().build() }
    single {
        Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/v4/")
            .client(get())
            .build()
    }

    single<RocketService> {
        get<Retrofit>().create(RocketService::class.java)
    }

    single { RocketRepository(get()) }
}