package pl.kacper.misterski.multiplatform.data.repository

import pl.kacper.misterski.multiplatform.data.db.dog.DogDao
import pl.kacper.misterski.multiplatform.data.mapper.mapToDogDataModels
import pl.kacper.misterski.multiplatform.data.mapper.mapToEntities
import pl.kacper.misterski.multiplatform.domain.model.DogDataModel
import pl.kacper.misterski.multiplatform.domain.repository.DogRepository

class DogRepositoryImpl(private val dogDao: DogDao) : DogRepository {
    override suspend fun getDogs(): List<DogDataModel> {
        return dogDao.getAllDogs().mapToDogDataModels()
    }

    override suspend fun insertDogs(dogs: List<DogDataModel>) {
        dogDao.insert(dogs.mapToEntities())
    }
}