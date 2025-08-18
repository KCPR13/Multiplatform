package pl.kacper.misterski.core.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetRecipesUseCase(
    private val recipeRepository: RecipeRepository
) {

    operator fun invoke() = flow {
        when (val result = recipeRepository.getRecipes()) {
            is DataResult.Success -> emit(DomainResult.Success(result.data))
            is DataResult.Error -> emit(DomainResult.Error(Exception(result.error.toString())))
        }
    }.flowOn(Dispatchers.IO)
}