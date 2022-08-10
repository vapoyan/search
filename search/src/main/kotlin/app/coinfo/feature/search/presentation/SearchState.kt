package app.coinfo.feature.search.presentation

import app.coinfo.feature.search.domain.model.SearchUI

internal data class SearchState(
    val isLoading: Boolean = false,
    val result: SearchUI = SearchUI(emptyList()),
    val error: String = ""
)
