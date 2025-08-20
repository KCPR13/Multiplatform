package pl.kacper.misterski.multiplatform.ui.start.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import pl.kacper.misterski.multiplatform.ui.navigation.NavigationItem
import pl.kacper.misterski.multiplatform.ui.start.StartScreen

fun NavGraphBuilder.start(onDogClick: () -> Unit) {
    composable(NavigationItem.Start.route) {
        StartScreen(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            onDogClick = onDogClick)
    }
}