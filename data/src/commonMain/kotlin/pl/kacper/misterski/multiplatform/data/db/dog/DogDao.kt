package pl.kacper.misterski.multiplatform.data.db.dog

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DogDao {

    @Query("SELECT * FROM ${DogEntity.TABLE_NAME}")
    suspend fun getAllDogs(): List<DogEntity>

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insert(dogs: List<DogEntity>)
}