package pl.kacper.misterski.multiplatform.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pl.kacper.misterski.multiplatform.core.PermissionHandler
import pl.kacper.misterski.multiplatform.core.PermissionHandlerImpl

actual val coreModule =
    module {
        singleOf(::PermissionHandlerImpl).bind<PermissionHandler>()

    }
