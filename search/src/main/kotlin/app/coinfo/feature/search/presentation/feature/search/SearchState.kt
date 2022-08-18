package app.coinfo.feature.search.presentation.feature.search

import app.coinfo.feature.search.domain.model.SearchResult

internal data class SearchState(
    val isLoading: Boolean = false,
    val result: SearchResult = SearchResult(emptyList()),
    val error: String = ""
)
