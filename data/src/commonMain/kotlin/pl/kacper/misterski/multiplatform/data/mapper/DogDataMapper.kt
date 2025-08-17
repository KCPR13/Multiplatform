package pl.kacper.misterski.multiplatform.data.mapper

import pl.kacper.misterski.multiplatform.data.db.dog.DogEntity
import pl.kacper.misterski.multiplatform.domain.model.DogDataModel


fun List<DogEntity>.mapToDogDataModels() =
   this.map { dogEntity ->
       DogDataModel(
           name = dogEntity.name
       )
    }


fun List<DogDataModel>.mapToEntities() =
    this.map { dogDataModel ->
        DogEntity(
            name = dogDataModel.name,
        )
    }