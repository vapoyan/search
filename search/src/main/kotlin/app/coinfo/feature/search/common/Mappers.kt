package app.coinfo.feature.search.common

import app.coinfo.feature.search.data.remote.dto.*
import app.coinfo.feature.search.domain.model.*

internal object Mappers {

    fun TrendingResultDto.toTrendingResult(coin: Map<String, PriceItemDto>) = TrendingResult(
        coins = coins.map { it.toTrendingCoin(coin) }
    )

    fun MarketDataResultDto.toRecentViewedResult(ids: List<String>) = RecentViewedResult(
        coins = ids.map { id -> this.first { coin -> coin.id == id }.toRecentViewedCoin() }
    )

    fun MarketDataResultDto.toSearchResult() = SearchResult(
        coins = this.map { it.toSearchResultCoin() }
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
            change24Hour = coin[id]?.price24hChange ?: 0.0
        )
    }

    private fun MarketDataDto.toRecentViewedCoin() = RecentViewedCoin(
        id = id,
        name = name,
        symbol = symbol,
        price = currentPrice ?: 0.0,
        imageUrl = image,
        priceChangePercentage24h = priceChangePercentage24h ?: 0.0
    )

    private fun MarketDataDto.toSearchResultCoin() = SearchCoin(
        id = id,
        name = name,
        symbol = symbol,
        price = currentPrice ?: 0.0,
        imageUrl = image,
        priceChangePercentage24h = priceChangePercentage24h ?: 0.0
    )
}