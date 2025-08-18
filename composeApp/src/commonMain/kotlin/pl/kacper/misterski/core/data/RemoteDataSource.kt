package pl.kacper.misterski.core.data

import io.ktor.client.statement.HttpResponse

interface RemoteDataSource {
    suspend fun getRecipes(): HttpResponse
}