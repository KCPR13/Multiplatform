package pl.kacper.misterski.multiplatform.ui.start

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

//TODO K co z resourcami
@Composable
fun StartScreen(modifier: Modifier, onDogClick: () -> Unit) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = onDogClick) {
            Text("Dogs")
        }
    }
}