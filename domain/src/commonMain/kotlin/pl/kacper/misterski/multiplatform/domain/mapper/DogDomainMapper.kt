package pl.kacper.misterski.multiplatform.domain.mapper

import pl.kacper.misterski.multiplatform.domain.model.DogDataModel
import pl.kacper.misterski.multiplatform.domain.model.DogDomainModel

fun List<DogDataModel>.mapToDomainModels(): List<DogDomainModel> {
    return this.map { dogDataModel ->
        DogDomainModel(
            name = dogDataModel.name
        )
    }
}