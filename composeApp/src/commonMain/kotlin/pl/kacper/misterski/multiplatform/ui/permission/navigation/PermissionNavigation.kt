package pl.kacper.misterski.multiplatform.ui.permission.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.icerock.moko.permissions.PermissionsController
import org.koin.compose.viewmodel.koinViewModel
import pl.kacper.misterski.multiplatform.ui.navigation.NavigationItem
import pl.kacper.misterski.multiplatform.ui.permission.PermissionScreen
import pl.kacper.misterski.multiplatform.ui.permission.PermissionScreenViewModel


fun NavGraphBuilder.permission(
    permissionsController: PermissionsController,
    onBackPressed: () -> Unit
) {
    composable(NavigationItem.Permission.route) {

        val viewModel = koinViewModel<PermissionScreenViewModel>()
        viewModel.setPermissionsController(permissionsController)

        PermissionScreen(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            openAppSettings = viewModel::openAppSettings,
            onBackPressed = onBackPressed,
            uiState = viewModel.uiState,
            requestLocationPermission = viewModel::provideLocationPermission,
            requestCameraPermission = viewModel::provideCameraPermission,
            provideBluetoothPermission = viewModel::provideBluetoothPermission
        )
    }
}