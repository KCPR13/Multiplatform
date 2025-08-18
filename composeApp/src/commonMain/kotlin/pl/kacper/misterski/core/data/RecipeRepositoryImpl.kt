package pl.kacper.misterski.core.data

import pl.kacper.misterski.core.domain.DataError
import pl.kacper.misterski.core.domain.DataResult
import pl.kacper.misterski.core.domain.RecipeInfo
import pl.kacper.misterski.core.domain.RecipeRepository
import pl.kacper.misterski.core.domain.Recipes
import pl.kacper.misterski.core.domain.map

class RecipeRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : RecipeRepository {

    override suspend fun getRecipes(): DataResult<Recipes, DataError> {
        return safeCall<RecipesDto> {
            remoteDataSource.getRecipes()
        }.map {
            it.mapToDomain()
        }
    }

}

fun RecipesDto.mapToDomain(): Recipes {
    return Recipes(
        recipes = results.map { it.mapToDomain() }
    )
}

fun RecipeInfoDto.mapToDomain(): RecipeInfo {
    return RecipeInfo(
        recipeId = id,
        recipeImage = image,
        recipeTitle = title,
        recipeMinutes = readyInMinutes,
        recipeScore = healthScore
    )
}