package pl.kacper.misterski.multiplatform.ui.permission

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.icerock.moko.permissions.PermissionState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionScreen(
    modifier: Modifier,
    requestLocationPermission: () -> Unit,
    requestCameraPermission: () -> Unit,
    provideBluetoothPermission: () -> Unit,
    openAppSettings: () -> Unit,
    onBackPressed: () -> Unit,
    uiState: PermissionScreenUiModel
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("App Permissions") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Manage your app permissions below.",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            PermissionStatusCard(
                permissionName = "Camera Access",
                status = uiState.cameraPermissionStatus,
                onRequestPermission = requestCameraPermission,
                onOpenSettings = openAppSettings
            )

            PermissionStatusCard(
                permissionName = "Location Access",
                status = uiState.locationPermissionStatus,
                onRequestPermission = requestLocationPermission,
                onOpenSettings = openAppSettings
            )

            PermissionStatusCard(
                permissionName = "Bluetooth",
                status = uiState.bluetoothPermissionStatus,
                onRequestPermission = provideBluetoothPermission,
                onOpenSettings = openAppSettings
            )
            Spacer(Modifier.weight(1f))
            Button(onClick = onBackPressed, modifier = Modifier.fillMaxWidth()) {
                Text("Back")
            }
        }
    }
}

@Composable
fun PermissionStatusCard(
    permissionName: String,
    status: PermissionState,
    onRequestPermission: () -> Unit,
    onOpenSettings: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = permissionName, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(end = 8.dp),
                    text = "Status: ${
                        status.name.replace('_', ' ').lowercase()
                            .replaceFirstChar { it.uppercase() }
                    }",
                    style = MaterialTheme.typography.labelLarge,
                    color = when (status) {
                        PermissionState.Granted -> MaterialTheme.colorScheme.primary
                        PermissionState.Denied -> MaterialTheme.colorScheme.error
                        PermissionState.DeniedAlways -> MaterialTheme.colorScheme.error
                        else -> LocalContentColor.current.copy(alpha = 0.6f)
                    }
                )

                when (status) {
                    PermissionState.Denied -> {
                        Button(onClick = onRequestPermission) {
                            Text(
                                "Request permission",
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    PermissionState.DeniedAlways -> {
                        Text(
                            "Denied always"
                        )
                    }

                    PermissionState.Granted -> Unit
                    else -> {
                        Button(onClick = onRequestPermission) {
                            Text(
                                "Request permission",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    }
                }
            }
        }
    }