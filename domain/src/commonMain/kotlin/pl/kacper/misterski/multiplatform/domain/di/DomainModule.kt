package pl.kacper.misterski.multiplatform.domain.di

import org.koin.dsl.module
import pl.kacper.misterski.multiplatform.domain.use_case.GetDogsUseCase
import pl.kacper.misterski.multiplatform.domain.use_case.SaveDogsUseCase


val useCaseModule = module {
    factory { GetDogsUseCase(get()) }
    factory { SaveDogsUseCase(get()) }
}


val domainModules = listOf(useCaseModule)