package pl.kacper.misterski.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pl.kacper.misterski.core.data.HttpClientFactory
import pl.kacper.misterski.core.data.RecipeRepositoryImpl
import pl.kacper.misterski.core.data.RemoteDataSource
import pl.kacper.misterski.core.data.RemoteDataSourceImpl
import pl.kacper.misterski.core.domain.GetRecipesUseCase
import pl.kacper.misterski.core.domain.RecipeRepository
import pl.kacper.misterski.core.presentation.RecipeViewModel

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::RemoteDataSourceImpl).bind<RemoteDataSource>()
    singleOf(::RecipeRepositoryImpl).bind<RecipeRepository>()
    factoryOf(::GetRecipesUseCase)
    viewModelOf(::RecipeViewModel)
}