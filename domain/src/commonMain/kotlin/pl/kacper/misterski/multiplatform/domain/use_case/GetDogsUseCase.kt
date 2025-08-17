package pl.kacper.misterski.multiplatform.domain.use_case

import kotlinx.coroutines.flow.flow
import pl.kacper.misterski.multiplatform.domain.repository.DogRepository
import pl.kacper.misterski.multiplatform.domain.util.DomainResult

//TODO K robimy commony też?

class GetDogsUseCase(private val dogRepository: DogRepository) {

    operator fun invoke() = flow {

        val dogs = dogRepository.getDogs()

        if (dogs.isNotEmpty()) {
            emit(DomainResult.Success(dogs))
        } else {
            emit(DomainResult.Error(Exception("No dogs found")))
        }

    }
}