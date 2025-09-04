package pl.kacper.misterski.multiplatform


import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import dev.icerock.moko.permissions.PermissionState
import pl.kacper.misterski.multiplatform.ui.permission.PermissionScreen
import pl.kacper.misterski.multiplatform.ui.permission.PermissionScreenUiModel
import kotlin.test.Test
import kotlin.test.assertTrue


//TODO link https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-test.html
class PermissionScreenTest {

    private val dummyUiState = PermissionScreenUiModel(
        cameraPermissionStatus = PermissionState.Denied,
        locationPermissionStatus = PermissionState.Granted,
        bluetoothPermissionStatus = PermissionState.DeniedAlways
    )

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun permission_screen_displays_all_permission_cards() = runComposeUiTest {
        //GIVEN
        setContent {
            PermissionScreen(
                modifier = Modifier,
                requestLocationPermission = {},
                requestCameraPermission = {},
                provideBluetoothPermission = {},
                openAppSettings = {},
                onBackPressed = {},
                uiState = dummyUiState
            )
        }


        //THEN
        onNodeWithText("App Permissions").assertIsDisplayed()
        onNodeWithText("Camera Access").assertIsDisplayed()
        onNodeWithText("Location Access").assertIsDisplayed()
        onNodeWithText("Bluetooth").assertIsDisplayed()
    }


    @OptIn(ExperimentalTestApi::class)
    @Test
    fun permission_screen_request_permission_button_for_denied_camera_is_clickable() =
        runComposeUiTest {

            //GIVEN
            var cameraRequested = false
            setContent {
                PermissionScreen(
                    modifier = Modifier,
                    requestLocationPermission = {},
                    requestCameraPermission = { cameraRequested = true },
                    provideBluetoothPermission = {},
                    openAppSettings = {},
                    onBackPressed = {},
                    uiState = dummyUiState
                )
            }

            //WHEN
            onNodeWithText("Request permission").performClick()

            //THEN
            assertTrue(cameraRequested)
        }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun permission_screen_back_button_triggers_on_back_pressed() = runComposeUiTest {
        //GIVEN
        var backPressed = false
        setContent {
            PermissionScreen(
                modifier = Modifier,
                requestLocationPermission = {},
                requestCameraPermission = {},
                provideBluetoothPermission = {},
                openAppSettings = {},
                onBackPressed = { backPressed = true },
                uiState = dummyUiState
            )
        }

        //WHEN
        onNodeWithText("Back").performClick()

        //THEN
        assertTrue(backPressed)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun permission_screen_status_colors_are_correct() = runComposeUiTest {
        // GIVEN
        setContent {
            PermissionScreen(
                modifier = Modifier,
                requestLocationPermission = {},
                requestCameraPermission = {},
                provideBluetoothPermission = {},
                openAppSettings = {},
                onBackPressed = {},
                uiState = dummyUiState
            )
        }

        // THEN
        onNodeWithText("Status: Denied").assertIsDisplayed()
        onNodeWithText("Status: Granted").assertIsDisplayed()
        onNodeWithText("Denied always").assertIsDisplayed()
    }
}