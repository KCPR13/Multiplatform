package pl.kacper.misterski.multiplatform.data.repository

import pl.kacper.misterski.multiplatform.data.db.AppRoom
import pl.kacper.misterski.multiplatform.data.mapper.mapToDogDataModels
import pl.kacper.misterski.multiplatform.data.mapper.mapToEntities
import pl.kacper.misterski.multiplatform.domain.model.DogDataModel
import pl.kacper.misterski.multiplatform.domain.repository.DogRepository

class DogRepositoryImpl(private val db: AppRoom) : DogRepository {

    //todo K clear it
    private val dogDao by lazy {
        db.dogDao()
    }

    override suspend fun getDogs(): List<DogDataModel> {
        return dogDao.getAllDogs().mapToDogDataModels()
    }

    override suspend fun insertDogs(dogs: List<DogDataModel>) {
        dogDao.insert(dogs.mapToEntities())
    }
}