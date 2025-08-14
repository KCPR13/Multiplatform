package pl.kacper.misterski.multiplatform.data.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import pl.kacper.misterski.multiplatform.data.repository.LandmarkRepositoryImpl
import pl.kacper.misterski.multiplatform.domain.repository.LandmarkRepository

val dataModule = module {
    singleOf(::LandmarkRepositoryImpl) { bind<LandmarkRepository>() }
}