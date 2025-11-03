package pl.kacper.misterski.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import coil3.compose.AsyncImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import pl.kacper.misterski.core.domain.RecipeInfo


@Composable
fun RecipesScreen() {
    KoinContext {
        val viewModel = koinViewModel<RecipeViewModel>()
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        val snackbarState = remember { SnackbarHostState() }

        ObserveAsEvents(viewModel.event) { event ->
            when (event) {
                is RecipeEvent.Error -> {
                    snackbarState.showSnackbar(event.message)
                }
            }
        }

        RecipeListScreen(
            state = state,
            snackbarState = snackbarState,
            onAction = viewModel::onAction
        )
    }
}

@Composable
fun RecipeListScreen(
    state: RecipeUiState,
    snackbarState: SnackbarHostState,
    onAction: (RecipeAction) -> Unit
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarState) }
    ) { paddingValues ->
        when (state) {
            is RecipeUiState.Loading -> LoadingScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            )

            is RecipeUiState.Success -> SuccessScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
                    .padding(paddingValues),
                data = state,
                onAction = onAction
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SuccessScreen(
    modifier: Modifier,
    data: RecipeUiState.Success,
    onAction: (RecipeAction) -> Unit
) {
    val pullToRefresh = rememberPullToRefreshState()

    PullToRefreshBox(
        modifier = modifier,
        state = pullToRefresh,
        isRefreshing = data.isRefreshing,
        onRefresh = { onAction(RecipeAction.RefreshData) }
    ) {
        if (!data.isRefreshing && data.filteredRecipes.isEmpty()) {
            EmptyScreen(
                modifier = Modifier
                    .fillMaxSize()
            )
        } else {
            Column(Modifier.fillMaxSize()) {
                LazyRow {
                    items(data.filters) { item ->
                        LetterCircle(item = item, onItemClick = {
                            onAction(RecipeAction.FilterItems(it))
                        })
                    }
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(
                        items = data.filteredRecipes,
                        key = { recipe -> recipe.recipeId }
                    ) { recipe ->
                        RecipeCard(recipe = recipe)
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier) {
        CircularProgressIndicator(Modifier.size(100.dp).align(Alignment.Center))
    }
}

@Composable
private fun EmptyScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text("Brak przepisów do wyświetlenia.")
    }
}


@Composable
private fun LetterCircle(
    item: FilterItem,
    modifier: Modifier = Modifier,
    onItemClick: (Char) -> Unit
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .padding(4.dp)
            .clickable(true, onClick = {
                onItemClick.invoke(item.letter)
            })
            .background(
                color = if (item.selected) Color.Black else Color.Gray,
                shape = androidx.compose.foundation.shape.CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = item.letter.toString(),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun RecipeCard(
    recipe: RecipeInfo,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                AsyncImage(
                    model = recipe.recipeImage,
                    contentDescription = recipe.recipeTitle,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(
                            color = Color.Black.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Like",
                        tint = Color.Red,
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = recipe.recipeScore.toString(),
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = recipe.recipeTitle,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Schedule,
                        contentDescription = "Czas przygotowania",
                        modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = "${recipe.recipeMinutes} min",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun <T> ObserveAsEvents(
    flow: Flow<T>,
    key1: Any? = null,
    key2: Any? = null,
    onEvent: suspend (T) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner, key1, key2) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                flow.collect(onEvent)
            }
        }
    }
}
