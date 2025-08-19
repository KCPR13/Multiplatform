package pl.kacper.misterski.multiplatform.data.di

import org.koin.dsl.module
import pl.kacper.misterski.multiplatform.data.db.AppRoom
import pl.kacper.misterski.multiplatform.data.db.dog.DogDao
import pl.kacper.misterski.multiplatform.data.db.getAppDatabase

actual val databaseModule = module {
    single<AppRoom> {
        getAppDatabase()
    }

    single<DogDao> {
        get<AppRoom>().dogDao()
    }
}
