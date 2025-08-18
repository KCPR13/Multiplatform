package pl.kacper.misterski.core.data

import kotlinx.serialization.Serializable

@Serializable
data class RecipesDto(
    val results: List<RecipeInfoDto>
)
