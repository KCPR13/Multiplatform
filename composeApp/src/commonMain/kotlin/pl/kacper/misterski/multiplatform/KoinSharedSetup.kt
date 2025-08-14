package pl.kacper.misterski.multiplatform

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import pl.kacper.misterski.multiplatform.data.di.dataModule
import pl.kacper.misterski.multiplatform.domain.di.domainModule

fun initKoin(configuration: KoinAppDeclaration? = null) {
    startKoin {
        includes(configuration)
        modules(dataModule, domainModule)
    }
}