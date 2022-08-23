package app.coinfo.feature.search.domain.model

internal data class RecentViewedCoins(
    val id: String,
    val name: String,
    val symbol: String,
    val imageUrl: String,
    val price: Double,
    val priceChangePercentage24h: Double
)
