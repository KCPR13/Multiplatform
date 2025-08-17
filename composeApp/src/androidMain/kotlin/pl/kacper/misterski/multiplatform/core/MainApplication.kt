package pl.kacper.misterski.multiplatform.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import pl.kacper.misterski.multiplatform.BuildConfig
import pl.kacper.misterski.multiplatform.data.di.dataModules
import pl.kacper.misterski.multiplatform.domain.di.domainModules

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            modules(
                *dataModules.toTypedArray(),
                *domainModules.toTypedArray()
            )
        }
    }
}