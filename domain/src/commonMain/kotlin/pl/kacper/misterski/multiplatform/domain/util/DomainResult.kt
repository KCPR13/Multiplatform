package pl.kacper.misterski.multiplatform.domain.util

sealed class DomainResult{
    data class Success<T>(val data: T) : DomainResult()
    data class Error(val exception: Throwable) : DomainResult()
}
