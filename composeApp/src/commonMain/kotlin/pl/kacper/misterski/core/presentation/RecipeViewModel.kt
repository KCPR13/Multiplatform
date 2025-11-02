package pl.kacper.misterski.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import pl.kacper.misterski.core.domain.DomainResult
import pl.kacper.misterski.core.domain.GetRecipesUseCase
import pl.kacper.misterski.core.domain.RecipeInfo

class RecipeViewModel(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    private val _event = Channel<RecipeEvent>()
    val event = _event.receiveAsFlow()

    private val _items = MutableStateFlow<List<RecipeInfo>>(emptyList())

    private val _filter = MutableStateFlow(('A'..'Z').map { letter ->
        FilterItem(letter = letter, selected = true)
    })

    init {
        fetchData()
    }

    val uiState: StateFlow<RecipesState> = combine(
        _items,
        _filter
    ) { items, filterItems ->
        val selectedFilters = filterItems.filter { it.selected }
        val filtered = if (filterItems.isEmpty()) items
        else items.filter { item ->
            selectedFilters.any {
                item.recipeTitle.startsWith(it.letter, true)
            }
        }

        RecipesState(
            filters = filterItems,
            listRecipes = filtered,
            isLoading = false
        )
    }.catch {
        _event.send(RecipeEvent.Error(it.message ?: "Unknown error"))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RecipesState(isLoading = true))

    private fun fetchData() {
        getRecipesUseCase().onEach { result ->
            when (result) {
                is DomainResult.Error -> {
                    _event.send(RecipeEvent.Error(result.error.message ?: "Unknown error"))

                }

                is DomainResult.Success -> {
                    _items.value = result.data.recipes
                }
            }

        }.catch {
            _event.send(RecipeEvent.Error(it.message ?: "Unknown error"))
        }.launchIn(viewModelScope)
    }

    fun onAction(action: RecipeAction) {
        when (action) {
            is RecipeAction.FilterItems -> {
                val currentFilters = _filter.value

                val newFilters = currentFilters.map { filterItem ->
                    if (filterItem.letter == action.newFilter) filterItem.copy(selected = !filterItem.selected) else filterItem
                }

                _filter.update { newFilters }

            }

            else -> fetchData()
        }
    }
}