package pl.kacper.misterski.multiplatform.data.di

import org.koin.dsl.module
import pl.kacper.misterski.multiplatform.data.db.AppRoom
import pl.kacper.misterski.multiplatform.data.db.dog.DogDao
import pl.kacper.misterski.multiplatform.data.db.getAppDatabase
import pl.kacper.misterski.multiplatform.data.repository.DogRepositoryImpl
import pl.kacper.misterski.multiplatform.domain.repository.DogRepository


//TODO K moduł per plik?
val databaseModule = module {
    single<AppRoom> {
        getAppDatabase()
    }
}
val dogModule = module {
    single<DogDao> {
        get<AppRoom>().dogDao()
    }

    single<DogRepository> {
        DogRepositoryImpl(
            get<DogDao>()
        )
    }
}

val dataModules = listOf(databaseModule, dogModule)