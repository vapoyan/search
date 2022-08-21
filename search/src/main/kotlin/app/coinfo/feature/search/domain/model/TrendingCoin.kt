package app.coinfo.feature.search.domain.model

internal data class TrendingCoin(
    val coinId: Int,
    val id: String,
    val large: String,
    val marketCapRank: Int,
    val name: String,
    val priceBtc: Double,
    val score: Int,
    val slug: String,
    val small: String,
    val symbol: String,
    val thumb: String,
    val change24Hour: Double?
)