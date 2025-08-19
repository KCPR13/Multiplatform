package pl.kacper.misterski.multiplatform.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import pl.kacper.misterski.multiplatform.data.di.dataModules
import pl.kacper.misterski.multiplatform.data.di.databaseModule
import pl.kacper.misterski.multiplatform.domain.di.domainModules
import pl.kacper.misterski.multiplatform.ui.di.viewModelModule


actual class KoinInitializer(private val config: KoinAppDeclaration? = null) {

    actual fun init(){
        startKoin {
            config?.invoke(this)
            modules(
                databaseModule +
                dataModules +
                        domainModules +
                        viewModelModule
            )
        }
    }
}
