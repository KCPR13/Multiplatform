package pl.kacper.misterski.multiplatform.ui.common

sealed class ViewState {
    class Loaded(): ViewState()
    class Loading(): ViewState()
    class Working(): ViewState()
    class Result(val result: ViewResultProvider): ViewState()

    companion object {
        fun loading(): ViewState {
            return Loading()
        }

        fun loaded(): ViewState {
            return Loaded()
        }

        fun working(): ViewState {
            return Working()
        }

        fun result(provider: ViewResultProvider): ViewState {
            return Result(provider)
        }
    }
}