package pl.kacper.misterski.multiplatform.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import pl.kacper.misterski.multiplatform.ui.dog.DogViewModel
import pl.kacper.misterski.multiplatform.ui.permission.PermissionScreenViewModel

actual val viewModelModule =
    module {
        viewModelOf(::DogViewModel)
        viewModelOf(::PermissionScreenViewModel)
    }
