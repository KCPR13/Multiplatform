package pl.kacper.misterski.multiplatform.data.repository

import pl.kacper.misterski.multiplatform.domain.model.LandmarkDataModel
import pl.kacper.misterski.multiplatform.domain.repository.LandmarkRepository

class LandmarkRepositoryImpl: LandmarkRepository {
    override suspend fun getAllLandmarks(): List<LandmarkDataModel> {
        return listOf(
            LandmarkDataModel(1, "Park Podolski", 51.7394946, 19.4923632),
            LandmarkDataModel(2, "Biedronka", 51.7394556, 19.4918246),
            LandmarkDataModel(3, "Kawiarnia", 51.73498421148127, 19.50532370215431),
            LandmarkDataModel(4, "Sklep Remont", 51.73672888085524, 19.494084238338868),
            LandmarkDataModel(5, "Kebab", 51.737292716108065, 19.494171457995385),
            LandmarkDataModel(6, "Sushi Kushi", 51.732937807067295, 19.49359000012158)
        )
    }

    override suspend fun insertLandmarks(landmarks: List<LandmarkDataModel>) {
        TODO("Not yet implemented")
    }
}