package pl.kacper.misterski.multiplatform.core

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import org.jetbrains.compose.ui.tooling.preview.Preview
import pl.kacper.misterski.multiplatform.ui.dog.navigation.dog
import pl.kacper.misterski.multiplatform.ui.navigation.NavigationItem
import pl.kacper.misterski.multiplatform.ui.permission.navigation.permission
import pl.kacper.misterski.multiplatform.ui.start.navigation.start


@Composable
@Preview
fun App() {
    MaterialTheme {
        val permissionFactory = rememberPermissionsControllerFactory()
        val controller = remember(permissionFactory) {
            permissionFactory.createPermissionsController()
        }

        BindEffect(controller)
        val navController = rememberNavController()

        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = NavigationItem.Start.route,
        ) {
            start(
                onDogClick = {
                    navController.navigate(NavigationItem.Dog.route)
                },
                onPermissionClick = {
                    navController.navigate(NavigationItem.Permission.route)
                }
            )
            dog(onBackPressed = { navController.navigateUp() })

            permission(
                onBackPressed = { navController.navigateUp() },
                permissionsController = controller,
            )
        }
    }


}