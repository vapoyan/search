package app.coinfo.feature.search.presentation.home

import app.coinfo.feature.search.domain.model.SearchResult

internal data class HomeState(
    val isLoading: Boolean = false,
    val result: SearchResult = SearchResult(emptyList()),
    val error: String = ""
)
