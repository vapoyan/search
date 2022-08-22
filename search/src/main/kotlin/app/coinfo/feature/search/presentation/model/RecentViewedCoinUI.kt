package app.coinfo.feature.search.presentation.model

import app.coinfo.feature.search.presentation.adapter.RecyclerViewItem

data class RecentViewedCoinUI(
    override val id: String,
    val symbol: String,
    val name: String,
    val imageUrl: String,
    val price: String,
    val priceChange24h: String
) : RecyclerViewItem
