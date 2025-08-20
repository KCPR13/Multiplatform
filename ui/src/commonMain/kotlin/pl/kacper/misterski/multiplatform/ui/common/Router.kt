package pl.kacper.misterski.multiplatform.ui.common

import kotlin.native.ObjCName

abstract class Router() {
    abstract fun handleRoute(@ObjCName("from") route: Routes)
}
