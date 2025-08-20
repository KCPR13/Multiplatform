package pl.kacper.misterski.multiplatform.ui.dog


sealed interface DogUiState {
    data object Loading : DogUiState
    data class Success(val dogs: List<DogUiModel>) : DogUiState
    data class Error(val message: String) : DogUiState
}