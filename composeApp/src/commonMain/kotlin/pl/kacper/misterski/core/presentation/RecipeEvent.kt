package pl.kacper.misterski.core.presentation


sealed interface RecipeEvent {
    data class Error(val message: String) : RecipeEvent

}
