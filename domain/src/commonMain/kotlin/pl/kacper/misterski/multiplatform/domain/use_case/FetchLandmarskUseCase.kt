package pl.kacper.misterski.multiplatform.domain.use_case

import kotlinx.coroutines.flow.flow
import pl.kacper.misterski.multiplatform.domain.common.DomainResult
import pl.kacper.misterski.multiplatform.domain.repository.LandmarkRepository

class FetchLandmarskUseCase(private val repository: LandmarkRepository) {
    operator fun invoke() = flow {

        val landmarks = repository.getAllLandmarks()

        if (landmarks.isNotEmpty()) {
            emit(DomainResult.Success(landmarks))
        } else {
            emit(DomainResult.Error(Exception("No landmarks found")))
        }
    }
}