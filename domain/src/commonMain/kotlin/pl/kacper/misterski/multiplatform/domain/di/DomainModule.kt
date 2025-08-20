package pl.kacper.misterski.multiplatform.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import pl.kacper.misterski.multiplatform.domain.use_case.GetDogsUseCase
import pl.kacper.misterski.multiplatform.domain.use_case.SaveDogsUseCase


val useCaseModule = module {

    factoryOf(::GetDogsUseCase)
    factoryOf(::SaveDogsUseCase)

}


val domainModules = listOf(useCaseModule)