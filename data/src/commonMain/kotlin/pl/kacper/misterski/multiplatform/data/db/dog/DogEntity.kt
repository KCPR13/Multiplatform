package pl.kacper.misterski.multiplatform.data.db.dog

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DogEntity.Companion.TABLE_NAME)
data class DogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
) {
    companion object {
        const val TABLE_NAME = "dogs"
    }
}