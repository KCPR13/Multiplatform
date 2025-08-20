package pl.kacper.misterski.multiplatform.LandmarksFeature.List

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import pl.kacper.misterski.multiplatform.domain.common.DomainResult
import pl.kacper.misterski.multiplatform.domain.model.LandmarkDataModel
import pl.kacper.misterski.multiplatform.domain.use_case.FetchLandmarskUseCase
import pl.kacper.misterski.multiplatform.ui.common.Router
import pl.kacper.misterski.multiplatform.ui.common.Routes
import pl.kacper.misterski.multiplatform.ui.common.ViewState

open class LandmarksViewModel(
    private val fetchLandmarks: FetchLandmarskUseCase,
    private val router: Router
): ViewModel() {
    private var _model = MutableStateFlow<LandmarksUIModel>(viewModelScope, LandmarksUIModel(emptyList()))
    @NativeCoroutinesState
    val model: StateFlow<LandmarksUIModel> = _model.asStateFlow()
    private var _viewState: MutableStateFlow<ViewState> =
        MutableStateFlow(viewModelScope, ViewState.Companion.loaded())
    @NativeCoroutinesState
    val viewState: StateFlow<ViewState> = _viewState.asStateFlow()

    fun onInput(input: Input) {
        when (input) {
            is Input.SelectedLandmark -> router.handleRoute(Routes.LandmarkDetails())
            is Input.AddLandmark -> router.handleRoute(Routes.AddLandmark())
        }
    }

    suspend fun loadLandmarks() {
        _viewState.value = ViewState.Companion.loading()
        viewModelScope.run {
            fetchLandmarks()
                .onEach {
                    when (it) {
                        is DomainResult.Error -> print("")
                        is DomainResult.Success<*> -> _model.value = LandmarksUIModel.mapped(it.data as List<LandmarkDataModel>)
                    }
                }
                .catch { }
                .collect()

            _viewState.value = ViewState.Companion.loaded()
        }
    }

    sealed class Input {
        class SelectedLandmark(val model: LandmarkDataModel): Input()
        class AddLandmark(): Input()
    }
}