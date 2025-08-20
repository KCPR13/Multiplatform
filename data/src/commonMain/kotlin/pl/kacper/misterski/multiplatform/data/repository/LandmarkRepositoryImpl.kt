package pl.kacper.misterski.multiplatform.data.repository

import pl.kacper.misterski.multiplatform.data.db.Landmarks.LandmarkDao
import pl.kacper.misterski.multiplatform.data.mappers.mapToEntities
import pl.kacper.misterski.multiplatform.data.mappers.mapToModels
import pl.kacper.misterski.multiplatform.domain.model.LandmarkDataModel
import pl.kacper.misterski.multiplatform.domain.model.LandmarkType
import pl.kacper.misterski.multiplatform.domain.repository.LandmarkRepository

class LandmarkRepositoryImpl(
    private val dao: LandmarkDao
): LandmarkRepository {
    override suspend fun getAllLandmarks(): List<LandmarkDataModel> {
        return dao.getAllLandmarks().mapToModels()
//        return listOf(
//            LandmarkDataModel(1, LandmarkType.park, "Park Podolski", 51.7394946, 19.4923632),
//            LandmarkDataModel(2, LandmarkType.market,"Biedronka", 51.7394556, 19.4918246),
//            LandmarkDataModel(3, LandmarkType.restaurant, "Kawiarnia", 51.73498421148127, 19.50532370215431),
//            LandmarkDataModel(4, LandmarkType.shop, "Sklep Remont", 51.73672888085524, 19.494084238338868),
//            LandmarkDataModel(5, LandmarkType.restaurant, "Kebab", 51.737292716108065, 19.494171457995385),
//            LandmarkDataModel(6, LandmarkType.restaurant, "Sushi Kushi", 51.732937807067295, 19.49359000012158)
//        )
    }

    override suspend fun insertLandmarks(landmarks: List<LandmarkDataModel>) {
        dao.insert(landmarks.mapToEntities())
    }
}