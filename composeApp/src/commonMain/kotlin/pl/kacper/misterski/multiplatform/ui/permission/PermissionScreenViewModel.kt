package pl.kacper.misterski.multiplatform.ui.permission

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.RequestCanceledException
import dev.icerock.moko.permissions.bluetooth.BLUETOOTH_SCAN
import dev.icerock.moko.permissions.camera.CAMERA
import dev.icerock.moko.permissions.location.COARSE_LOCATION
import kotlinx.coroutines.launch
import pl.kacper.misterski.multiplatform.core.PermissionHandler

class PermissionScreenViewModel(
    private val permissionHandler: PermissionHandler
) : ViewModel() {

    private lateinit var permissionsController: PermissionsController

    var uiState by mutableStateOf(PermissionScreenUiModel())
        private set


    fun provideLocationPermission() {
        viewModelScope.launch {
            val status = try {
                permissionsController.providePermission(Permission.COARSE_LOCATION)
                PermissionState.Granted
            } catch (_: DeniedAlwaysException) {
                PermissionState.DeniedAlways
            } catch (_: DeniedException) {
                PermissionState.Denied
            } catch (_: RequestCanceledException) {
                PermissionState.NotGranted
            }

            uiState = uiState.copy(locationPermissionStatus = status)
        }
    }

    fun provideCameraPermission() {
        viewModelScope.launch {
            val status = try {
                permissionsController.providePermission(Permission.CAMERA)
                PermissionState.Granted
            } catch (_: DeniedAlwaysException) {
                PermissionState.DeniedAlways
            } catch (_: DeniedException) {
                PermissionState.Denied
            } catch (_: RequestCanceledException) {
                PermissionState.NotGranted
            }
            uiState = uiState.copy(cameraPermissionStatus = status)
        }
    }

    fun provideBluetoothPermission() {
        viewModelScope.launch {
            val status = try {
                permissionsController.providePermission(Permission.BLUETOOTH_SCAN)
                PermissionState.Granted
            } catch (_: DeniedAlwaysException) {
                PermissionState.DeniedAlways
            } catch (_: DeniedException) {
                PermissionState.Denied
            } catch (_: RequestCanceledException) {
                PermissionState.NotGranted
            }
            uiState = uiState.copy(bluetoothPermissionStatus = status)

        }
    }

    fun openAppSettings() = permissionHandler.openAppSettings()
    fun setPermissionsController(permissionsController: PermissionsController) {
      this.permissionsController = permissionsController
        viewModelScope.launch {
            uiState = uiState.copy(
                locationPermissionStatus = permissionsController.getPermissionState(Permission.COARSE_LOCATION),
                cameraPermissionStatus = permissionsController.getPermissionState(Permission.CAMERA),
                bluetoothPermissionStatus = permissionsController.getPermissionState(Permission.BLUETOOTH_SCAN)
            )
        }
    }

}