package pl.kacper.misterski.multiplatform.core

import android.app.Application
import pl.kacper.misterski.multiplatform.di.KoinInitializer

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinInitializer(applicationContext).init()
    }
}