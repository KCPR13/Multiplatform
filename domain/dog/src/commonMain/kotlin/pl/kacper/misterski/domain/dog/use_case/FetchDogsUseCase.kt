package pl.kacper.misterski.domain.dog.use_case

import kotlinx.coroutines.flow.flow
import pl.kacper.misterski.domain.dog.repository.DogRepository

//TODO K robimy commony też?
//TODO K separate file?
sealed class DomainResult{
    data class Success<T>(val data: T) : DomainResult()
    data class Error(val exception: Throwable) : DomainResult()
}

class FetchDogsUseCase(private val dogRepository: DogRepository) {

    operator fun invoke() = flow {

        val dogs = dogRepository.getDogs()

        if (dogs.isNotEmpty()) {
            emit(DomainResult.Success(dogs))
        } else {
            emit(DomainResult.Error(Exception("No dogs found")))
        }

    }
}