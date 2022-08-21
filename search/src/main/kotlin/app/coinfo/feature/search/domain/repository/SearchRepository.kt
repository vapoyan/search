package app.coinfo.feature.search.domain.repository

import app.coinfo.feature.search.data.remote.dto.PriceItemDto
import app.coinfo.feature.search.data.remote.dto.SearchResultDto
import app.coinfo.feature.search.data.remote.dto.TrendingResultDto

internal interface SearchRepository {

    /**
     * Search for coins, categories and markets listed on CoinGecko ordered by
     * largest Market Cap first
     */
    suspend fun search(query: String): SearchResultDto

    /**
     * Gets Top-7 trending coins on CoinGecko as searched by users in the last 24
     * hours (Ordered by most popular first)
     */
    suspend fun trending(): TrendingResultDto

    /**
     * Get the price of given cryptocurrency ids in euro
     */
    suspend fun getPrice(ids: List<String>): Map<String, PriceItemDto>
}