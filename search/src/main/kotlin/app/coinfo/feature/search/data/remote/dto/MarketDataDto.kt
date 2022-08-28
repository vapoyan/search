package app.coinfo.feature.search.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MarketDataDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("ath")
    val ath: Double? = null,
    @SerializedName("ath_change_percentage")
    val athChangePercentage: Double? = null,
    @SerializedName("ath_date")
    val athDate: String? = null,
    @SerializedName("atl")
    val atl: Double? = null,
    @SerializedName("atl_change_percentage")
    val atlChangePercentage: Double? = null,
    @SerializedName("atl_date")
    val atlDate: String? = null,
    @SerializedName("circulating_supply")
    val circulatingSupply: Double? = null,
    @SerializedName("current_price")
    val currentPrice: Double? = null,
    @SerializedName("fully_diluted_valuation")
    val fullyDilutedValuation: Double? = null,
    @SerializedName("high_24h")
    val high24h: Double? = null,
    @SerializedName("image")
    val image: String,
    @SerializedName("last_updated")
    val lastUpdated: String? = null,
    @SerializedName("low_24h")
    val low24h: Double? = null,
    @SerializedName("market_cap")
    val marketCap: Double? = null,
    @SerializedName("market_cap_change_24h")
    val marketCapChange24h: Double? = null,
    @SerializedName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24h: Double? = null,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int? = null,
    @SerializedName("max_supply")
    val maxSupply: Double? = null,
    @SerializedName("name")
    val name: String,
    @SerializedName("price_change_24h")
    val priceChange24h: Double? = null,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double? = null,
    @SerializedName("roi")
    val roi: Any? = null,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("total_supply")
    val totalSupply: Double? = null,
    @SerializedName("total_volume")
    val totalVolume: Double? = null,
    @SerializedName("price_change_percentage_1h_in_currency")
    val priceChangePercentage1hInCurrency: Double? = null,
    @SerializedName("price_change_percentage_24h_in_currency")
    val priceChangePercentage24hInCurrency: Double? = null,
    @SerializedName("price_change_percentage_7d_in_currency")
    val priceChangePercentage7dInCurrency: Double? = null,
)