package pl.kacper.misterski.multiplatform

import androidx.compose.ui.window.ComposeUIViewController
import pl.kacper.misterski.adaptive.ReplyApp
import pl.kacper.misterski.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { ReplyApp() }