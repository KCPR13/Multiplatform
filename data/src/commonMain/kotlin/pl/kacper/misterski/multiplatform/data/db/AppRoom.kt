package pl.kacper.misterski.multiplatform.data.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabaseConstructor
import pl.kacper.misterski.multiplatform.data.db.dog.DogDao
import pl.kacper.misterski.multiplatform.data.db.dog.DogEntity

@Database(
    entities = [DogEntity::class],
    version = 1,
    exportSchema = true
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppRoom : androidx.room.RoomDatabase() {

    abstract fun dogDao(): DogDao



    companion object {
        const val DATABASE_NAME = "app.db"
    }
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppRoom> {
    override fun initialize(): AppRoom
}
