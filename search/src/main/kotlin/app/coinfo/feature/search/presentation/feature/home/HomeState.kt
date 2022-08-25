package app.coinfo.feature.search.presentation.feature.home

import app.coinfo.feature.search.domain.model.RecentViewedResult
import app.coinfo.feature.search.domain.model.SearchResult
import app.coinfo.feature.search.domain.model.TrendingResult

internal data class HomeState(
    val isLoading: Boolean = false,
    val trendingCoinsResult: TrendingResult = TrendingResult(emptyList()),
    val recentViewedCoinsResult: RecentViewedResult = RecentViewedResult(emptyList()),
    val searchCoinsResult: SearchResult = SearchResult(emptyList()),
    val error: String = ""
)
