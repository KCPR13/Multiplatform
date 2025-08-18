package pl.kacper.misterski.multiplatform

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import pl.kacper.misterski.core.presentation.RecipesScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        RecipesScreen()
    }
}