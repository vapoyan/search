package app.coinfo.feature.search.data.remote.dto

import com.google.gson.annotations.SerializedName

internal data class ExchangeDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("large")
    val large: String,
    @SerializedName("market_type")
    val marketType: String? = null,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumb")
    val thumb: String
)