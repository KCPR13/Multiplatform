package pl.kacper.misterski.multiplatform.ui.dog.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import pl.kacper.misterski.multiplatform.ui.dog.DogScreen
import pl.kacper.misterski.multiplatform.ui.dog.DogUiState
import pl.kacper.misterski.multiplatform.ui.dog.DogViewModel
import pl.kacper.misterski.multiplatform.ui.navigation.NavigationItem
import org.koin.compose.viewmodel.koinViewModel


fun NavGraphBuilder.dog(onBackPressed: () -> Unit) {
    composable(NavigationItem.Dog.route) {

        val viewModel = koinViewModel<DogViewModel>()
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