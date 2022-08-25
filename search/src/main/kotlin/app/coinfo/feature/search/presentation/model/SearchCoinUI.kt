package app.coinfo.feature.search.presentation.model

import app.coinfo.feature.search.presentation.adapter.RecyclerViewItem

internal data class SearchCoinUI(
    override val id: String,
    val symbol: String,
    val name: String,
    val imageUrl: String,
    val priceChange24h: String,
    val price: String,
    val trend: Boolean,
) : RecyclerViewItem