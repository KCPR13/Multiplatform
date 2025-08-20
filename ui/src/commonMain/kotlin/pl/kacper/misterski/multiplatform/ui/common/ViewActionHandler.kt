package pl.kacper.misterski.multiplatform.ui.common

import kotlin.native.ObjCName

abstract class ViewActionHandler {
    constructor() { }
    abstract fun performAction(@ObjCName("from") action: Router)
}