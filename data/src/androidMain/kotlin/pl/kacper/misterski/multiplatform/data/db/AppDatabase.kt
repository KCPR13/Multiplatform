package pl.kacper.misterski.multiplatform.data.db

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import pl.kacper.misterski.multiplatform.data.db.AppRoom.Companion.DATABASE_NAME

fun getAppDatabase(context: Context): AppRoom {
    val dbFile = context.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder<AppRoom>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}