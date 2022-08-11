package app.coinfo.feature.search.domain.model

internal data class Coin(
    val id: String,
    val large: String,
    val marketCapRank: Int,
    val name: String,
    val symbol: String,
    val thumb: String
)