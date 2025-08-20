package pl.kacper.misterski.multiplatform.ui.di

import org.koin.dsl.module
import pl.kacper.misterski.multiplatform.ui.common.Router
import kotlin.native.ObjCName

val uiModule = module {

}

fun <PlatformRouter: Router> registerRouter(@ObjCName("_") router: PlatformRouter) {
    val routerModule = module {
        single { router as Router }
    }
    uiModule.includes(routerModule)
}