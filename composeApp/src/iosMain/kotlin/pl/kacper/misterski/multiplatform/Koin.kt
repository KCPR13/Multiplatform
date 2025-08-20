package pl.kacper.misterski.multiplatform

import org.koin.mp.KoinPlatform
import pl.kacper.misterski.multiplatform.LandmarksFeature.List.LandmarksViewModel
import pl.kacper.misterski.multiplatform.domain.repository.LandmarkRepository

class Koin() {
    fun landmarksViewModel(): LandmarksViewModel = KoinPlatform.getKoin().get()
}