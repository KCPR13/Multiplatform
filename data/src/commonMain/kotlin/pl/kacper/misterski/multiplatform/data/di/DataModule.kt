package pl.kacper.misterski.multiplatform.data.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import pl.kacper.misterski.multiplatform.data.db.AppRoom
import pl.kacper.misterski.multiplatform.data.db.Landmarks.LandmarkDao
import pl.kacper.misterski.multiplatform.data.db.getAppDatabase
import pl.kacper.misterski.multiplatform.data.repository.LandmarkRepositoryImpl
import pl.kacper.misterski.multiplatform.domain.repository.LandmarkRepository

val dataModule = module {
    single {
        getAppDatabase()
    }
    single {
        get<AppRoom>().landmarksDao()
    }
    singleOf(::LandmarkRepositoryImpl) { bind<LandmarkRepository>() }
}