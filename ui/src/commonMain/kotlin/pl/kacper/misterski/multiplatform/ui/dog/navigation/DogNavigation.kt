package pl.kacper.misterski.multiplatform.ui.dog.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.compose.koinInject
import pl.kacper.misterski.multiplatform.ui.dog.DogScreen
import pl.kacper.misterski.multiplatform.ui.dog.DogViewModel
import pl.kacper.misterski.multiplatform.ui.navigation.NavigationItem

fun NavGraphBuilder.dog(onBackPressed: () -> Unit) {
    composable(NavigationItem.Dog.route) {

        val viewModel: DogViewModel = koinInject()
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        DogScreen(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            state = state,
            onBackPressed = onBackPressed,
        )
    }
}