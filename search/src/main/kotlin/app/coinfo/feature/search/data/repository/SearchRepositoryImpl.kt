package app.coinfo.feature.search.data.repository

import app.coinfo.feature.search.data.remote.CoingeckoApi
import app.coinfo.feature.search.data.remote.dto.SearchDto
import app.coinfo.feature.search.domain.repository.SearchRepository
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(
    private val api: CoingeckoApi
) : SearchRepository {

    override suspend fun search(query: String): SearchDto {
        return api.search(query)
    }
}