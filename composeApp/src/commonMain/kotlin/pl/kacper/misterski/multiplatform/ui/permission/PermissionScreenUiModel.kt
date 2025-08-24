package pl.kacper.misterski.multiplatform.ui.permission

import dev.icerock.moko.permissions.PermissionState

data class PermissionScreenUiModel(
    val locationPermissionStatus: PermissionState = PermissionState.NotDetermined,
    val cameraPermissionStatus: PermissionState = PermissionState.NotDetermined,
    val bluetoothPermissionStatus: PermissionState = PermissionState.NotDetermined
)