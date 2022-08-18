package app.coinfo.feature.search.data.remote

import app.coinfo.feature.search.data.remote.dto.SearchResultDto
import app.coinfo.feature.search.data.remote.dto.TrendingResultDto
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CoingeckoApi {

    @GET("v3/search")
    suspend fun search(@Query("query") query: String): SearchResultDto

    @GET("v3/search/trending")
    suspend fun trending(): TrendingResultDto
}