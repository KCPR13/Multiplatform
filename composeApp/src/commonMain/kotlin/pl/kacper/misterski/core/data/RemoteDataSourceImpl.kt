package pl.kacper.misterski.core.data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse

const val BASE_URL = "https://api.spoonacular.com"

class RemoteDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteDataSource {
    override suspend fun getRecipes(): HttpResponse {
        return httpClient.get(
            urlString = "$BASE_URL/recipes/complexSearch"
        ) {
            parameter("apiKey", "46984f7b40134257bdb52e676fefe9a1")
            parameter("number", "20")
            parameter("addRecipeInformation", "true")
        }
    }
}