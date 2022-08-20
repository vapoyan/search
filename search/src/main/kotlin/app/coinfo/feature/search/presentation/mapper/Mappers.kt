package app.coinfo.feature.search.presentation.mapper

import app.coinfo.feature.search.R
import app.coinfo.feature.search.domain.model.TrendingResult
import app.coinfo.feature.search.presentation.adapter.RecyclerViewData
import app.coinfo.feature.search.presentation.model.TrendingCoinUI

internal object Mappers {

    @JvmStatic
    fun TrendingResult?.toTrendingCoinsUI() = RecyclerViewData(
        R.layout.search_item_trending_coins,
        this?.coins?.map {
            TrendingCoinUI(
                id = it.id,
                symbol = it.symbol,
                imageUrl = it.large
            )
        } ?: emptyList()
    )
}