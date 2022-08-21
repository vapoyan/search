package app.coinfo.feature.search.presentation.mapper

import app.coinfo.feature.search.R
import app.coinfo.feature.search.domain.model.TrendingResult
import app.coinfo.feature.search.presentation.adapter.RecyclerViewData
import app.coinfo.feature.search.presentation.model.TrendingCoinUI
import kotlin.math.abs

internal object Mappers {

    @JvmStatic
    fun TrendingResult?.toTrendingCoinsUI() = RecyclerViewData(
        R.layout.search_item_trending_coins,
        this?.coins?.map {
            TrendingCoinUI(
                id = it.id,
                symbol = it.symbol,
                imageUrl = it.large,
                priceChange24H = "%.2f".format(abs(it.change24Hour ?: 0.0)),
                priceChangeImage = if ((it.change24Hour ?: 0.0).compareTo(0.0) > 0) {
                    R.drawable.search_ic_up
                } else R.drawable.search_ic_down
            )
        } ?: emptyList()
    )
}