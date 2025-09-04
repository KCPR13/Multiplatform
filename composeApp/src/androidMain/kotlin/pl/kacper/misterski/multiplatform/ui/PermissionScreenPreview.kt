package pl.kacper.misterski.multiplatform.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import dev.icerock.moko.permissions.PermissionState
import pl.kacper.misterski.multiplatform.ui.core.MyAppTheme
import pl.kacper.misterski.multiplatform.ui.permission.PermissionScreen
import pl.kacper.misterski.multiplatform.ui.permission.PermissionScreenUiModel

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Preview(
    name = "Galaxy Fold",
    device = Devices.FOLDABLE,
    showBackground = true
)
@Preview(name = "Foldable Small", device = "spec:width=673dp,height=841dp,dpi=480")
@Preview(
    name = "Galaxy Z Flip Cover Screen",
    device = "spec:width=260px,height=512px,dpi=420",
    showBackground = true
)
@Composable
fun PreviewPermissionScreen() {
    MyAppTheme {
        PermissionScreen(
            modifier = Modifier,
            requestLocationPermission = {},
            requestCameraPermission = {},
            provideBluetoothPermission = {},
            openAppSettings = {},
            onBackPressed = {},
            uiState = PermissionScreenUiModel(
                locationPermissionStatus = PermissionState.Denied,
                cameraPermissionStatus = PermissionState.Granted,
                bluetoothPermissionStatus = PermissionState.NotGranted
            )
        )
    }
}
