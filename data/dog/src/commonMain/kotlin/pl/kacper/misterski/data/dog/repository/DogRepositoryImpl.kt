package pl.kacper.misterski.data.dog.repository

import pl.kacper.misterski.domain.dog.model.DogDataModel
import pl.kacper.misterski.domain.dog.repository.DogRepository

class DogRepositoryImpl: DogRepository {
    override suspend fun getDogs(): List<DogDataModel> {
        // Simulating a network call or database query
        return listOf(
            DogDataModel(name = "Labrador Retriever"),
            DogDataModel(name = "German Shepherd"),
            DogDataModel(name = "Golden Retriever"),
            DogDataModel(name = "Bulldog"),
            DogDataModel(name = "Beagle")
        )
    }
}