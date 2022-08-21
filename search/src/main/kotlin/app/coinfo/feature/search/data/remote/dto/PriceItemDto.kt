package app.coinfo.feature.search.data.remote.dto

import com.google.gson.annotations.SerializedName

internal data class PriceItemDto(
    @SerializedName("eur")
    val price: Double,
    @SerializedName("eur_24h_change")
    val price24hChange: Double,
    @SerializedName("eur_24h_vol")
    val price24hVol: Double,
    @SerializedName("eur_market_cap")
    val marketCap: Double,
    @SerializedName("last_updated_at")
    val lastUpdatedAt: Int
)