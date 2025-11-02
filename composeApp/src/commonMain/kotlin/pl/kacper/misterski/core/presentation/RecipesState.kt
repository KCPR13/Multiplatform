package pl.kacper.misterski.core.presentation

import pl.kacper.misterski.core.domain.RecipeInfo

data class RecipesState(
    val listRecipes: List<RecipeInfo> = emptyList(),
    val filters: List<FilterItem> =  emptyList(),
    val isLoading: Boolean = false
)
