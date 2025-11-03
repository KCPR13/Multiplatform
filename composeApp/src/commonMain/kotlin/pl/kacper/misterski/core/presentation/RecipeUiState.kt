package pl.kacper.misterski.core.presentation

import pl.kacper.misterski.core.domain.RecipeInfo

sealed interface RecipeUiState {
    data object Loading : RecipeUiState
    data class Success(
        val recipes: List<RecipeInfo>,
        val filters: List<FilterItem>,
        val isRefreshing: Boolean = false,
    ) : RecipeUiState {
        val filteredRecipes: List<RecipeInfo>
            get() {
                val selectedFilters = filters.filter { it.selected }
                if (selectedFilters.size == filters.size || selectedFilters.isEmpty()) {
                    return recipes
                }
                return recipes.filter { recipe ->
                    selectedFilters.any { filter ->
                        recipe.recipeTitle.startsWith(filter.letter, true)
                    }
                }
            }
    }
}
