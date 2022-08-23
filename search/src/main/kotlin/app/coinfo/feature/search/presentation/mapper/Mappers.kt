package app.coinfo.feature.search.presentation.mapper

import app.coinfo.feature.search.R
import app.coinfo.feature.search.domain.model.RecentViewedResult
import app.coinfo.feature.search.domain.model.TrendingResult
import app.coinfo.feature.search.presentation.adapter.RecyclerViewData
import app.coinfo.feature.search.presentation.model.RecentViewedCoinUI
import app.coinfo.feature.search.presentation.model.TrendingCoinUI
import kotlin.math.abs

internal object Mappers {

    @JvmStatic
    fun TrendingResult?.toTrendingCoinsUI() = RecyclerViewData(
        R.layout.search_item_trending_coins,
        this?.coins?.map { coin ->
            TrendingCoinUI(
                id = coin.id,
                symbol = coin.symbol,
                imageUrl = coin.large,
                priceChange24H = "%.2f%%".format(abs(coin.change24Hour)),
                trend = coin.change24Hour.compareTo(0.0) > 0
            )
        } ?: emptyList()
    )

    @JvmStatic
    fun RecentViewedResult?.toRecentViewedCoinsUI() = RecyclerViewData(
        R.layout.search_item_recent_viewed_coins,
        this?.coins?.map { coin ->
            RecentViewedCoinUI(
                id = coin.id,
                name = coin.name,
                symbol = coin.symbol,
                imageUrl = coin.imageUrl,
                price = "€%.2f".format(abs(coin.price)),
                priceChange24h = "%.2f%%".format(abs(coin.priceChangePercentage24h)),
                trend = coin.priceChangePercentage24h.compareTo(0.0) > 0
            )
        } ?: emptyList()
    )
}