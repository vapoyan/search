package app.coinfo.feature.search.domain.repository

import app.coinfo.feature.search.data.remote.dto.SearchResultDto
import app.coinfo.feature.search.data.remote.dto.TrendingResultDto

internal interface SearchRepository {

    suspend fun search(query: String): SearchResultDto

    suspend fun trending(): TrendingResultDto
}