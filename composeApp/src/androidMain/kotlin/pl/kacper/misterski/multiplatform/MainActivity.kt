package pl.kacper.misterski.multiplatform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.kacper.misterski.adaptive.ReplyApp
import pl.kacper.misterski.adaptive.ReplyHomeViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: ReplyHomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ReplyApp(
                replyHomeUIState = uiState,
                onEmailClick = viewModel::setSelectedEmail
            )
        }
    }
}