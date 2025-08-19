package pl.kacper.misterski.multiplatform

import androidx.compose.ui.window.ComposeUIViewController
import pl.kacper.misterski.multiplatform.di.KoinInitializer

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) { App() }