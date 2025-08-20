package pl.kacper.misterski.multiplatform.ui.common

import pl.kacper.misterski.multiplatform.ui.common.ViewState

interface SharedViewModel<I> {
    val viewState: ViewState
    fun <I> onInput(input: I)
}