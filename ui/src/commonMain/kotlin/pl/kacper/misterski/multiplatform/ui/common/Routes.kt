package pl.kacper.misterski.multiplatform.ui.common

sealed class Routes {
    class LandmarkDetails(): Routes()
    class AddLandmark: Routes()
}