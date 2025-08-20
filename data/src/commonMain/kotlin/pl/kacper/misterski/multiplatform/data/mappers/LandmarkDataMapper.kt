package pl.kacper.misterski.multiplatform.data.mappers

import pl.kacper.misterski.multiplatform.data.db.Landmarks.LandmarkEntity
import pl.kacper.misterski.multiplatform.domain.model.LandmarkDataModel

fun List<LandmarkEntity>.mapToModels(): List<LandmarkDataModel> = this.map { entity ->
    LandmarkDataModel(entity.id, entity.type, entity.name, entity.latitude, entity.longitude, entity.landmarkDescription)
}

fun List<LandmarkDataModel>.mapToEntities(): List<LandmarkEntity> = this.map { model ->
    LandmarkEntity(model.id, model.type, model.name, model.latitude, model.longitude, model.landmarkDescription)
}