package pl.kacper.misterski.multiplatform.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import pl.kacper.misterski.multiplatform.ui.dog.DogViewModel
import pl.kacper.misterski.multiplatform.ui.permission.PermissionScreenViewModel

actual val viewModelModule =
    module {
        singleOf(::DogViewModel)
        singleOf(::PermissionScreenViewModel)
    }