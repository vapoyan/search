package app.coinfo.feature.search.presentation.model

import app.coinfo.feature.search.presentation.adapter.RecyclerViewItem

internal data class TrendingCoinUI(
    override val id: String,
    val symbol: String,
    val imageUrl: String,
    val priceChange24H: String,
    val trend: Boolean,
) : RecyclerViewItem