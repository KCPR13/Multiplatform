package pl.kacper.misterski.core.presentation

sealed interface RecipeAction {
    data class FilterItems(val newFilter: Char) : RecipeAction

    data object RefreshData : RecipeAction
}