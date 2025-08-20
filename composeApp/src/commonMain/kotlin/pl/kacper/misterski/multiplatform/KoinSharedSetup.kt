package pl.kacper.misterski.multiplatform

import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.dsl.module
import pl.kacper.misterski.multiplatform.LandmarksFeature.List.LandmarksViewModel
import pl.kacper.misterski.multiplatform.data.di.dataModule
import pl.kacper.misterski.multiplatform.domain.di.domainModule
import pl.kacper.misterski.multiplatform.ui.di.uiModule

fun initKoin(configuration: KoinAppDeclaration? = null) {
    startKoin {
        includes(configuration)
        modules(dataModule, domainModule, uiModule, appModule)
    }
}

val appModule = module {
    factoryOf(::LandmarksViewModel)
}