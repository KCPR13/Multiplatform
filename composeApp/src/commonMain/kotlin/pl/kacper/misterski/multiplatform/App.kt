package pl.kacper.misterski.multiplatform

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import pl.kacper.misterski.multiplatform.ui.dog.navigation.dog
import pl.kacper.misterski.multiplatform.ui.navigation.NavigationItem
import pl.kacper.misterski.multiplatform.ui.start.navigation.start


@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = NavigationItem.Start.route,
        ) {
            start(
                onDogClick = {
                    navController.navigate(NavigationItem.Dog.route)
                }
            )

            dog(onBackPressed = { navController.navigateUp() })
        }
    }
}