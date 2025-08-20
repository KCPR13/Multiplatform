package pl.kacper.misterski.multiplatform.LandmarksFeature.Details

import pl.kacper.misterski.multiplatform.domain.model.LandmarkDataModel

data class LandmarkDetailsUIModel(val name: String, val location: Pair<Double, Double>, val description: String?) {
    companion object {
        fun mapped(model: LandmarkDataModel): LandmarkDetailsUIModel {
            return LandmarkDetailsUIModel(
                model.name,
                Pair(model.latitude, model.longitude),
                model.landmarkDescription)
        }
    }
}
