package app.coinfo.feature.search.presentation

import app.coinfo.feature.search.domain.model.TrendingResult
import app.coinfo.feature.search.presentation.model.TrendingCoinUI

internal object Mappers {

    @JvmStatic
    fun TrendingResult?.toTrendingCoinsUI() =
        this?.coins?.map {
            TrendingCoinUI(
                id = it.id,
                symbol = it.symbol,
                imageUrl = it.large
            )
        } ?: emptyList()
}