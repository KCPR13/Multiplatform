package pl.kacper.misterski.core.domain

sealed interface DomainResult<out D, out E> {
    data class Success<D>(val data: D) : DomainResult<D, Nothing>
    data class Error(val error: Throwable) : DomainResult<Nothing, Exception>
}