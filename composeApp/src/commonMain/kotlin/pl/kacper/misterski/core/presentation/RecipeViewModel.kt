package pl.kacper.misterski.core.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import pl.kacper.misterski.core.domain.DomainResult
import pl.kacper.misterski.core.domain.GetRecipesUseCase

class RecipeViewModel(
    getRecipesUseCase: GetRecipesUseCase
): ViewModel() {

    var state by mutableStateOf(RecipesState())
    private set

    init {
        state = state.copy(
            isLoading = true
        )
        getRecipesUseCase.invoke()
            .onEach { result ->
                state = when (result) {
                    is DomainResult.Success -> {
                        state.copy(
                            listRecipes = result.data.recipes,
                            isLoading = false,
                            isError = false
                        )
                    }

                    is DomainResult.Error -> {
                        state.copy(
                            isLoading = false,
                            isError = true
                        )
                    }
                }
            }.catch {
                state = state.copy(
                    isLoading = false,
                    isError = true
                )
            }.launchIn(viewModelScope)
    }

}