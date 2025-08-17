package pl.kacper.misterski.multiplatform.ui.dog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DogScreen(modifier: Modifier, state: DogUiState, onBackPressed: () -> Unit) {

    Column(modifier, verticalArrangement = Arrangement.Center) {
        TopBar(
            onBackPressed = onBackPressed
        )

        when (state) {
            is DogUiState.Error -> ErrorScreen(message = state.message)
            DogUiState.Loading -> CircularProgressIndicator(
                modifier = Modifier.fillMaxSize().height(48.dp)
            )

            is DogUiState.Success -> {
                LazyColumn {
                    items(items = state.dogs) { item ->
                        Dog(item)
                    }
                }
            }
        }
        FloatingActionButton(onBackPressed) {
            Text("Back")
        }
    }
}

@Composable
private fun TopBar(onBackPressed: () -> Unit) {
    Row {
        Text(
            modifier = Modifier.clickable(onClick = onBackPressed),
            text = "<",
            fontSize = 30.sp,
        )


        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Dogs",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
private fun ErrorScreen(message: String) {
    Text(
        text = message,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.error
    )

}

@Composable
private fun Dog(dog: DogUiModel) {
    Card(
        Modifier.fillMaxWidth().padding(16.dp),
        shape = CardDefaults.outlinedShape
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = dog.name,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
