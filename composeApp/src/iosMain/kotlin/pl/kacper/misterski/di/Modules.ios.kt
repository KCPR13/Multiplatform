package pl.kacper.misterski.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import pl.kacper.misterski.core.presentation.RecipeViewModel

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { Darwin.create() }
    }

actual val viewModelModule = module {
    singleOf(::RecipeViewModel)

}