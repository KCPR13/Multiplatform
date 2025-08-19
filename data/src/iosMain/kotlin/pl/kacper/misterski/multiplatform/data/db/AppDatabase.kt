package pl.kacper.misterski.multiplatform.data.db

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import pl.kacper.misterski.multiplatform.data.db.AppRoom.Companion.DATABASE_NAME
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual fun getAppDatabase(): AppRoom {
    return getDatabaseBuilder()
        .build()
}

private fun getDatabaseBuilder(): RoomDatabase.Builder<AppRoom> {
    val dbFilePath = documentDirectory() + DATABASE_NAME
    return Room.databaseBuilder<AppRoom>(
        name = dbFilePath,
    )
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}