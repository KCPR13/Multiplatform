package pl.kacper.misterski.multiplatform.ui.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import pl.kacper.misterski.multiplatform.ui.dog.DogViewModel

actual val viewModelModule =
    module {
        singleOf(::DogViewModel)
    }