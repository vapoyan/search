package app.coinfo.feature.search.data.remote.dto

internal data class TrendingResultDto(
    val coins: List<TrendingCoinDto>,
    val exchanges: List<Any>
)