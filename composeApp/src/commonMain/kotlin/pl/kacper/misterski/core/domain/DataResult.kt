package pl.kacper.misterski.core.domain

sealed interface DataResult<out D, out E : Error> {
    data class Success<out D>(val data: D) : DataResult<D, Nothing>
    data class Error<out E : pl.kacper.misterski.core.domain.Error>(val error: E) :
        DataResult<Nothing, E>
}

inline fun <T, E: Error, R> DataResult<T, E>.map(map: (T) -> R): DataResult<R, E> {
    return when(this) {
        is DataResult.Error -> DataResult.Error(error)
        is DataResult.Success -> DataResult.Success(map(data))
    }
}
