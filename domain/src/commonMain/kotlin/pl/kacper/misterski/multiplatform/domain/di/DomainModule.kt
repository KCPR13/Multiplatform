package pl.kacper.misterski.multiplatform.domain.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import pl.kacper.misterski.multiplatform.domain.use_case.FetchLandmarskUseCase

val domainModule = module {
    factoryOf(::FetchLandmarskUseCase)
}