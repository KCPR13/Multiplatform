package pl.kacper.misterski.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import pl.kacper.misterski.core.presentation.RecipeViewModel

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { OkHttp.create()}
    }

actual val viewModelModule = module {
    viewModelOf(::RecipeViewModel)
}