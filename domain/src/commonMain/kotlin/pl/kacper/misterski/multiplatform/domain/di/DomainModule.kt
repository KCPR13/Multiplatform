package pl.kacper.misterski.multiplatform.domain.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.qualifier.named
import org.koin.dsl.module
import pl.kacper.misterski.multiplatform.domain.use_case.GetDogsUseCase
import pl.kacper.misterski.multiplatform.domain.use_case.SaveDogsUseCase



val useCaseModule = module {
    single<CoroutineDispatcher>(named(DispatcherNames.IO)) {
        Dispatchers.IO
    }

    factory { GetDogsUseCase(get(), get(named(DispatcherNames.IO))) }
    factory { SaveDogsUseCase(get()) }

}


val domainModules = listOf(useCaseModule)