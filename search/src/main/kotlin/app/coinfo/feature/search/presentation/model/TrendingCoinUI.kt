package app.coinfo.feature.search.presentation.model

import app.coinfo.feature.search.common.widget.recyclerview.RecyclerViewDataItem

internal data class TrendingCoinUI(
    override val id: String,
    val symbol: String,
    val imageUrl: String,
) : RecyclerViewDataItem