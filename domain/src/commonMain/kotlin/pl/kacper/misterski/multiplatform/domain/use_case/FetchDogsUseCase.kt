package pl.kacper.misterski.multiplatform.domain.use_case

import kotlinx.coroutines.flow.flow
import pl.kacper.misterski.multiplatform.domain.common.DomainResult
import pl.kacper.misterski.multiplatform.domain.repository.DogRepository

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