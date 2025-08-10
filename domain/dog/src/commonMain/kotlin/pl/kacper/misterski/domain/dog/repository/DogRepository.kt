package pl.kacper.misterski.domain.dog.repository

import pl.kacper.misterski.domain.dog.model.DogDataModel

interface DogRepository {

    suspend fun getDogs(): List<DogDataModel>
}