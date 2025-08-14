package pl.kacper.misterski.multiplatform.data.db.Landmarks

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LandmarkEntity.TABLE_NAME)
data class LandmarkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double
) {
    companion object {
        const val TABLE_NAME = "landmarks"
    }
}
