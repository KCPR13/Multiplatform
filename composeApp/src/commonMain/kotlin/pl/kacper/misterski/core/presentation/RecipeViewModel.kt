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

    private val _uiState = MutableStateFlow(RecipeUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = Channel<RecipeEvent>()
    val event = _event.receiveAsFlow()

    init {
        fetchData(isInitialLoad = true)
    }

    private fun fetchData(isInitialLoad: Boolean = false, isRefreshing: Boolean = false) {
        _uiState.update {
            it.copy(
                isLoading = isInitialLoad,
                isRefreshing = isRefreshing
            )
        }

        getRecipesUseCase().onEach { result ->
            when (result) {
                is DomainResult.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isRefreshing = false,
                            recipes = result.data.recipes
                        )
                    }
                }

                is DomainResult.Error -> {
                    val errorMessage = result.error.message ?: "Unknown error"
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isRefreshing = false,
                        )
                    }
                    _event.send(RecipeEvent.Error(errorMessage))
                }
            }
        }.catch { throwable ->
            val errorMessage = throwable.message ?: "Unknown error"
            _uiState.update {
                it.copy( // TODO jak zrobie copy i mialem itemki to pokaże stare z errorem, trzeba by czyscic state
                    isLoading = false,
                    isRefreshing = false,
                )
            }
            _event.send(RecipeEvent.Error(errorMessage))
        }.launchIn(viewModelScope)
    }

    fun onAction(action: RecipeAction) {
        when (action) {
            is RecipeAction.FilterItems -> {
                _uiState.update { currentState ->
                    val newFilters = currentState.filters.map { filterItem ->
                        if (filterItem.letter == action.newFilter) {
                            filterItem.copy(selected = !filterItem.selected)
                        } else {
                            filterItem
                        }
                    }
                    currentState.copy(filters = newFilters)
                }
            }

            is RecipeAction.RefreshData -> {
                fetchData(isRefreshing = true)
            }
        }
    }
}