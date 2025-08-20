package pl.kacper.misterski.multiplatform.LandmarksFeature.List

import pl.kacper.misterski.multiplatform.domain.model.LandmarkDataModel
import kotlin.native.ShouldRefineInSwift

data class LandmarksUIModel(val landmarks: List<Landmark>) {
    data class Landmark(
        val id: Int,
        val name: String,
        @ShouldRefineInSwift val location: Pair<Double, Double>)
    companion object {
        fun mapped(fromModels: List<LandmarkDataModel>): LandmarksUIModel {
            val mapped = fromModels.map { model ->
                Landmark(model.id,
                    model.name,
                    Pair(model.latitude, model.longitude))
            }
            return LandmarksUIModel(mapped)
        }
    }
}
