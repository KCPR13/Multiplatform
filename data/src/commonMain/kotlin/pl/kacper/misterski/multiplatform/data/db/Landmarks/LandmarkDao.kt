package pl.kacper.misterski.multiplatform.data.db.Landmarks

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LandmarkDao {
    @Query("SELECT * FROM ${LandmarkEntity.TABLE_NAME}")
    suspend fun getAllLandmarks(): List<LandmarkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(landmarks: List<LandmarkEntity>)
}