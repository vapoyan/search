package app.coinfo.feature.search.common

import app.coinfo.feature.search.data.remote.dto.CoinDto
import app.coinfo.feature.search.data.remote.dto.SearchResultDto
import app.coinfo.feature.search.data.remote.dto.TrendingCoinDto
import app.coinfo.feature.search.data.remote.dto.TrendingResultDto
import app.coinfo.feature.search.domain.model.SearchCoin
import app.coinfo.feature.search.domain.model.SearchResult
import app.coinfo.feature.search.domain.model.TrendingCoin
import app.coinfo.feature.search.domain.model.TrendingResult
import app.coinfo.feature.search.presentation.home.adapter.TrendingCoinUI

internal object Mappers {

    fun SearchResultDto.toSearchResult() = SearchResult(
        coins = coins.map { it.toSearchCoin() }
    )

    fun TrendingResultDto.toTrendingResult() = TrendingResult(
        coins = coins.map { it.toTrendingCoin() }
    )

    @JvmStatic
    fun TrendingResult?.toTrendingCoinsUI() =
        this?.coins?.map {
            TrendingCoinUI(
                id = it.id,
                symbol = it.symbol,
                thumbnailUrl = it.large
            )
        } ?: emptyList()


    private fun CoinDto.toSearchCoin() = SearchCoin(
        id = id,
        large = large,
        marketCapRank = marketCapRank,
        name = name,
        symbol = symbol,
        thumb = thumb,
    )

    private fun TrendingCoinDto.toTrendingCoin() = with(item) {
        TrendingCoin(
            id = id,
            coinId = coin_id,
            large = large,
            marketCapRank = market_cap_rank,
            priceBtc = price_btc,
            name = name,
            slug = slug,
            small = small,
            symbol = symbol,
            thumb = thumb,
            score = score,
        )
    }
}