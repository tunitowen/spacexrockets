package dev.tonyowen.spacex

import android.app.Application
import dev.tonyowen.spacex.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class SpaceApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@SpaceApplication)
            modules(appModule)
        }

    }
}