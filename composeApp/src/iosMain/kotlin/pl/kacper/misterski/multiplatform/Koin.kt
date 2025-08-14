package pl.kacper.misterski.multiplatform

import org.koin.mp.KoinPlatform
import pl.kacper.misterski.multiplatform.domain.use_case.FetchLandmarskUseCase

sealed class Koin {
    fun makeFetchLandmarkUseCase(): FetchLandmarskUseCase = KoinPlatform.getKoin().get()
}