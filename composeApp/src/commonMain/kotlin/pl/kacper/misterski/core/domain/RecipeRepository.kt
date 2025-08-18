package pl.kacper.misterski.core.domain

interface RecipeRepository {
    suspend fun getRecipes(): DataResult<Recipes, DataError>
}