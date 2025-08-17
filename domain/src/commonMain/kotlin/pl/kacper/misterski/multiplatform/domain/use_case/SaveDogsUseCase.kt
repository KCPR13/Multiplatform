package pl.kacper.misterski.multiplatform.domain.use_case

import kotlinx.coroutines.flow.flow
import pl.kacper.misterski.multiplatform.domain.model.DogDataModel
import pl.kacper.misterski.multiplatform.domain.repository.DogRepository
import pl.kacper.misterski.multiplatform.domain.util.DomainResult


class SaveDogsUseCase(private val dogRepository: DogRepository) {

    operator fun invoke(dogs: List<DogDataModel>) = flow {
        dogRepository.insertDogs(dogs)
        emit(DomainResult.Success(Unit))
    }
}