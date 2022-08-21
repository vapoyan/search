package app.coinfo.feature.search.data.remote

import app.coinfo.feature.search.data.remote.dto.PriceItemDto
import app.coinfo.feature.search.data.remote.dto.SearchResultDto
import app.coinfo.feature.search.data.remote.dto.TrendingResultDto
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CoingeckoApi {

    @GET("v3/search")
    suspend fun search(@Query("query") query: String): SearchResultDto

    @GET("v3/search/trending")
    suspend fun getTrendingCoins(): TrendingResultDto

    /**
     * Gets the current price of any cryptocurrency in any other supported currencies that you need
     *
     * @param ids ids of coins, comma-separated if query more then 1
     * @param currencies currencies of coins, comma-separated if querying more than 1
     */
    @GET("v3/simple/price")
    suspend fun getPrice(
        @Query("ids") ids: String,
        @Query("vs_currencies") currencies: String = "eur",
        @Query("include_market_cap") includeMarketCap: Boolean = true,
        @Query("include_24hr_vol") include24hVol: Boolean = true,
        @Query("include_24hr_change") include24hChanged: Boolean = true,
        @Query("include_last_updated_at") includeLastUpdateAt: Boolean = true
    ): Map<String, PriceItemDto>
}