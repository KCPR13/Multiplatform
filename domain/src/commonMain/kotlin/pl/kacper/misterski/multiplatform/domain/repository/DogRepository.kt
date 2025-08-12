package pl.kacper.misterski.multiplatform.domain.repository

import pl.kacper.misterski.multiplatform.domain.model.DogDataModel

interface DogRepository {

    suspend fun getDogs(): List<DogDataModel>

    suspend fun insertDogs(dogs: List<DogDataModel>)
}