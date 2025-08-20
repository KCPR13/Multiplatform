package pl.kacper.misterski.multiplatform.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pl.kacper.misterski.multiplatform.data.repository.DogRepositoryImpl
import pl.kacper.misterski.multiplatform.domain.repository.DogRepository


val dogModule = module {
    singleOf(::DogRepositoryImpl).bind<DogRepository>()
}

val dataModules = listOf(dogModule)