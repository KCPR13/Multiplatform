package pl.kacper.misterski.multiplatform.data.db.Landmarks

import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.kacper.misterski.multiplatform.domain.model.LandmarkType

@Entity(tableName = LandmarkEntity.TABLE_NAME)
data class LandmarkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val type: LandmarkType,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val landmarkDescription: String?
) {
    companion object {
        const val TABLE_NAME = "landmarks"
    }
}