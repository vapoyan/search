package app.coinfo.feature.search.data.repository

import app.coinfo.feature.search.data.remote.CoingeckoApi
import app.coinfo.feature.search.data.remote.dto.SearchResultDto
import app.coinfo.feature.search.data.remote.dto.TrendingResultDto
import app.coinfo.feature.search.domain.repository.SearchRepository
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(
    private val api: CoingeckoApi
) : SearchRepository {

    override suspend fun search(query: String): SearchResultDto {
        return api.search(query)
    }

    override suspend fun trending(): TrendingResultDto {
        return api.trending()
    }
}