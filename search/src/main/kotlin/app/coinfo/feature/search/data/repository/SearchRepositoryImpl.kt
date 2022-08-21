package app.coinfo.feature.search.data.repository

import android.util.Log
import app.coinfo.feature.search.data.remote.CoingeckoApi
import app.coinfo.feature.search.data.remote.dto.PriceItemDto
import app.coinfo.feature.search.data.remote.dto.SearchResultDto
import app.coinfo.feature.search.data.remote.dto.TrendingResultDto
import app.coinfo.feature.search.domain.repository.SearchRepository
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(
    private val api: CoingeckoApi
) : SearchRepository {

    override suspend fun search(query: String): SearchResultDto = api.search(query)

    override suspend fun trending(): TrendingResultDto {
        Log.i(TAG, "Get Trending Coins")
        val result = api.getTrendingCoins()
        Log.d(TAG, "  < Trending Coins : $result")
        return result
    }

    override suspend fun getPrice(
        ids: List<String>
    ): Map<String, PriceItemDto> {
        Log.d(TAG, "Get Price")
        val stringListIds = ids.joinToString(separator = ",")
        Log.d(TAG, "  > Ids    : $stringListIds")
        val result = api.getPrice(ids = stringListIds)
        Log.d(TAG, "  < Prices : $result")
        return result
    }

    companion object {
        private const val TAG = "SearchRepositoryImpl"
    }
}