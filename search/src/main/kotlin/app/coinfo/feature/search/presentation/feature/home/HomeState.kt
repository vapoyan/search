package app.coinfo.feature.search.presentation.feature.home

import app.coinfo.feature.search.domain.model.TrendingResult

internal data class HomeState(
    val isLoading: Boolean = false,
    val result: TrendingResult = TrendingResult(emptyList()),
    val error: String = ""
)
