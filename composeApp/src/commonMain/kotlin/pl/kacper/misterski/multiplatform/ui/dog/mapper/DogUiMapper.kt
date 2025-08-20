package pl.kacper.misterski.multiplatform.ui.dog.mapper

import pl.kacper.misterski.multiplatform.domain.model.DogDomainModel
import pl.kacper.misterski.multiplatform.ui.dog.DogUiModel

fun List<DogDomainModel>.mapToUiModels() =
    this.map { dogDomainModel ->
        DogUiModel(
            name = dogDomainModel.name,
        )
    }