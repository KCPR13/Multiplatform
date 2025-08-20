package pl.kacper.misterski.multiplatform.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import pl.kacper.misterski.multiplatform.BuildConfig
import pl.kacper.misterski.multiplatform.data.di.dataModules
import pl.kacper.misterski.multiplatform.data.di.databaseModule
import pl.kacper.misterski.multiplatform.domain.di.domainModules

actual class KoinInitializer(private val context: Context) {

    actual fun init(){
        startKoin {
            androidContext(context)
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            modules(
                databaseModule + dataModules + domainModules + viewModelModule
            )
        }
    }
}