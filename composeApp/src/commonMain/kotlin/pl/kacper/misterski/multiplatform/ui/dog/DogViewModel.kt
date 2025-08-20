package pl.kacper.misterski.multiplatform.ui.dog


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import pl.kacper.misterski.multiplatform.domain.model.DogDomainModel
import pl.kacper.misterski.multiplatform.domain.use_case.GetDogsUseCase
import pl.kacper.misterski.multiplatform.domain.util.DomainResult
import pl.kacper.misterski.multiplatform.ui.dog.DogUiState.Error
import pl.kacper.misterski.multiplatform.ui.dog.DogUiState.Loading
import pl.kacper.misterski.multiplatform.ui.dog.DogUiState.Success
import pl.kacper.misterski.multiplatform.ui.dog.mapper.mapToUiModels

class DogViewModel(getDogsUseCase: GetDogsUseCase) : ViewModel() {

    private val _uiState =
        MutableStateFlow<DogUiState>(Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getDogsUseCase().onEach { result ->
            when (result) {
                is DomainResult.Error -> _uiState.update { Error(result.exception.message.orEmpty()) }
                is DomainResult.Success<*> -> (result.data as? ArrayList<DogDomainModel>)?.let { domainModels ->
                    _uiState.update { Success(domainModels.mapToUiModels()) }
                } ?: kotlin.run {
                    _uiState.update { Error("Dogs fetch error") }
                }
            }
        }.catch { error ->
            error.printStackTrace()
            _uiState.update { Error(error.message.orEmpty()) }
        }.launchIn(viewModelScope)
    }
}