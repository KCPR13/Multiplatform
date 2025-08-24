package pl.kacper.misterski.multiplatform.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pl.kacper.misterski.multiplatform.core.PermissionHandler
import pl.kacper.misterski.multiplatform.core.PermissionHandlerImpl


actual val coreModule =
    module {
        single<PermissionHandler> {
            PermissionHandlerImpl(androidContext())
        }
    }
