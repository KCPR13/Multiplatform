package pl.kacper.misterski.multiplatform.data.repository

import pl.kacper.misterski.multiplatform.domain.model.DogDataModel
import pl.kacper.misterski.multiplatform.domain.repository.DogRepository

class DogRepositoryImpl : DogRepository {
    override suspend fun getDogs(): List<DogDataModel> {
        return listOf(
            DogDataModel(name = "Labrador Retriever"),
            DogDataModel(name = "German Shepherd"),
            DogDataModel(name = "Golden Retriever"),
            DogDataModel(name = "Bulldog"),
            DogDataModel(name = "Beagle")
        )
    }

    override suspend fun insertDogs(dogs: List<DogDataModel>) {
        TODO("Not yet implemented")
    }
}