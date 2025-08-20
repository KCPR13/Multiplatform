package pl.kacper.misterski.multiplatform.domain.model

import kotlin.native.RefinesInSwift

data class LandmarkDataModel(val id: Int, val type: LandmarkType, val name: String, val latitude: Double, val longitude: Double, val landmarkDescription: String?)

enum class LandmarkType {
    restaurant,
    shop,
    market,
    park
}
