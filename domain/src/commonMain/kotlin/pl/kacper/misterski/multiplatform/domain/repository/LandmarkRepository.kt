package pl.kacper.misterski.multiplatform.domain.repository

import pl.kacper.misterski.multiplatform.domain.model.LandmarkDataModel

interface LandmarkRepository {
    suspend fun getAllLandmarks(): List<LandmarkDataModel>

    suspend fun insertLandmarks(landmarks: List<LandmarkDataModel>)
}