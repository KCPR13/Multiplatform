package pl.kacper.misterski.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import pl.kacper.misterski.core.domain.DomainResult
import pl.kacper.misterski.core.domain.GetRecipesUseCase

class RecipeViewModel(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<RecipeUiState>(RecipeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _event = Channel<RecipeEvent>()
    val event = _event.receiveAsFlow()

    init {
        fetchData(isInitialLoad = true)
    }

    private fun fetchData(isInitialLoad: Boolean = false, isRefreshing: Boolean = false) {
        if (isInitialLoad) {
            _uiState.update { RecipeUiState.Loading }
        }

        if (isRefreshing) {
            val currentState = _uiState.value
            if (currentState is RecipeUiState.Success) {
                _uiState.update { currentState.copy(isRefreshing = true) }
            }
        }

        getRecipesUseCase().onEach { result ->
            when (result) {
                is DomainResult.Success -> {
                    val currentState = _uiState.value
                    val currentFilters = if (currentState is RecipeUiState.Success) {
                        currentState.filters
                    } else {
                        ('A'..'Z').map { FilterItem(letter = it, selected = true) }
                    }

                    _uiState.update {
                        RecipeUiState.Success(
                            recipes = result.data.recipes,
                            filters = currentFilters,
                            isRefreshing = false
                        )
                    }
                }
                is DomainResult.Error -> {
                    val errorMessage = result.error.message ?: "Unknown error"
                    _event.send(RecipeEvent.Error(errorMessage))
                }
            }
        }.catch { throwable ->
            val errorMessage = throwable.message ?: "Unknown error"
            _event.send(RecipeEvent.Error(errorMessage))
        }.launchIn(viewModelScope)
    }

    fun onAction(action: RecipeAction) {
        when (action) {
            is RecipeAction.FilterItems -> {
                val currentState = _uiState.value
                if (currentState is RecipeUiState.Success) {
                    val newFilters = currentState.filters.map { filterItem ->
                        if (filterItem.letter == action.newFilter) {
                            filterItem.copy(selected = !filterItem.selected)
                        } else {
                            filterItem
                        }
                    }
                    _uiState.value = currentState.copy(filters = newFilters)
                }
            }
            is RecipeAction.RefreshData -> {
                fetchData(isRefreshing = true)
            }
        }
    }
}