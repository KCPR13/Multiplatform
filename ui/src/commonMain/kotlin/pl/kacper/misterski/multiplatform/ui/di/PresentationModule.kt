package pl.kacper.misterski.multiplatform.ui.di

import org.koin.dsl.module
import pl.kacper.misterski.multiplatform.ui.dog.DogViewModel

val viewModelModule = module {
    factory { DogViewModel(get()) }
}


val uiModules = listOf(viewModelModule)