package app.coinfo.feature.search.common

import app.coinfo.feature.search.data.remote.dto.*
import app.coinfo.feature.search.domain.model.SearchCoin
import app.coinfo.feature.search.domain.model.SearchResult
import app.coinfo.feature.search.domain.model.TrendingCoin
import app.coinfo.feature.search.domain.model.TrendingResult

internal object Mappers {

    fun SearchResultDto.toSearchResult() = SearchResult(
        coins = coins.map { it.toSearchCoin() }
    )

    fun TrendingResultDto.toTrendingResult(coin: Map<String, PriceItemDto>) = TrendingResult(
        coins = coins.map { it.toTrendingCoin(coin) }
    )

    private fun CoinDto.toSearchCoin() = SearchCoin(
        id = id,
        large = large,
        marketCapRank = marketCapRank,
        name = name,
        symbol = symbol,
        thumb = thumb,
    )

    private fun TrendingCoinDto.toTrendingCoin(coin: Map<String, PriceItemDto>) = with(item) {
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
            change24Hour = coin[id]?.price24hChange
        )
    }
}