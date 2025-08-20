package pl.kacper.misterski.multiplatform.LandmarksFeature.Details

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import pl.kacper.misterski.multiplatform.domain.model.LandmarkDataModel
import pl.kacper.misterski.multiplatform.ui.common.ViewState

open class LandmarkDetailsViewModel(val landmark: LandmarkDataModel): ViewModel() {
    private var _model: MutableStateFlow<LandmarkDetailsUIModel> = MutableStateFlow(viewModelScope, LandmarkDetailsUIModel.mapped(landmark))
    @NativeCoroutinesState
    val model = _model.asStateFlow()
    private var _viewState: MutableStateFlow<ViewState> =
        MutableStateFlow(viewModelScope, ViewState.Companion.loaded())
    @NativeCoroutinesState
    val viewState: StateFlow<ViewState> = _viewState.asStateFlow()
}