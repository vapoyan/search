package app.coinfo.feature.search.domain.repository

import app.coinfo.feature.search.data.remote.dto.SearchDto

internal interface SearchRepository {

    suspend fun search(query: String): SearchDto
}