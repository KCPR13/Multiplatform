package pl.kacper.misterski.multiplatform.data.db

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import pl.kacper.misterski.multiplatform.data.db.AppRoom.Companion.DATABASE_NAME
import platform.Foundation.NSHomeDirectory

fun getAppDatabase(): AppRoom {
    val dbFile = NSHomeDirectory() + DATABASE_NAME
    return Room.databaseBuilder<AppRoom>(
        name = dbFile,
        factory = { AppRoom::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}