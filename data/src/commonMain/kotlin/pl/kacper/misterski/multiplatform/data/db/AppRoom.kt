package pl.kacper.misterski.multiplatform.data.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import pl.kacper.misterski.multiplatform.data.db.Landmarks.LandmarkDao
import pl.kacper.misterski.multiplatform.data.db.Landmarks.LandmarkEntity
import pl.kacper.misterski.multiplatform.data.db.dog.DogDao
import pl.kacper.misterski.multiplatform.data.db.dog.DogEntity

@Database(
    entities = [DogEntity::class, LandmarkEntity::class],
    version = 2,
    exportSchema = true
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppRoom : androidx.room.RoomDatabase() {

    abstract fun dogDao(): DogDao
    abstract fun landmarksDao(): LandmarkDao

    companion object {
        const val DATABASE_NAME = "app.db"
    }
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppRoom> {
    override fun initialize(): AppRoom
}