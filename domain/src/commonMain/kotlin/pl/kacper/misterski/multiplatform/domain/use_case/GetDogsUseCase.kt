package pl.kacper.misterski.multiplatform.domain.use_case

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import pl.kacper.misterski.multiplatform.domain.mapper.mapToDomainModels
import pl.kacper.misterski.multiplatform.domain.model.DogDataModel
import pl.kacper.misterski.multiplatform.domain.repository.DogRepository
import pl.kacper.misterski.multiplatform.domain.util.DomainResult

//TODO K robimy commony też?

class GetDogsUseCase(private val dogRepository: DogRepository) {

    operator fun invoke() = flow {
        insertDogs()
        val dogs = dogRepository.getDogs()

        if (dogs.isNotEmpty()) {
            emit(DomainResult.Success(dogs.mapToDomainModels()))
        } else {
            emit(DomainResult.Error(Exception("No dogs found")))
        }

    }.flowOn(Dispatchers.IO)


    private suspend fun insertDogs() {

        val dogNamesList = listOf("Burek", "Reksio", "Azor", "Fafik", "Max", "Luna", "Bella")

        dogRepository.insertDogs(dogNamesList.map { DogDataModel(it) })
    }
}