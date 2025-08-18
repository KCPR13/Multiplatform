package pl.kacper.misterski.core.data

import kotlinx.serialization.Serializable

@Serializable
data class RecipeInfoDto(
    val id: Int,
    val image: String,
    val title: String,
    val readyInMinutes: Int,
    val healthScore: Double
)