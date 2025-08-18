package pl.kacper.misterski.multiplatform

import android.app.Application
import org.koin.android.ext.koin.androidContext
import pl.kacper.misterski.di.initKoin

class RecipeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@RecipeApplication)
        }
    }
}