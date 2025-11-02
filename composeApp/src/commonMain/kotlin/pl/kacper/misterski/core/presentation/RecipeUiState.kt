package pl.kacper.misterski.core.presentation

import pl.kacper.misterski.core.domain.RecipeInfo

data class RecipeUiState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val recipes: List<RecipeInfo> = emptyList(),
    val filters: List<FilterItem> = ('A'..'Z').map { FilterItem(letter = it, selected = true) }
) {
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