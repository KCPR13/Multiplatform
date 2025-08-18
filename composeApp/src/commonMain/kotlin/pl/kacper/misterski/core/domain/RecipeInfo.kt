package pl.kacper.misterski.core.domain

data class RecipeInfo(
    val recipeId: Int,
    val recipeImage: String,
    val recipeTitle: String,
    val recipeMinutes: Int,
    val recipeScore: Double
)
