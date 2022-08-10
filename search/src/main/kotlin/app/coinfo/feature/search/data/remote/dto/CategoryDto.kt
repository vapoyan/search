package app.coinfo.feature.search.data.remote.dto

import com.google.gson.annotations.SerializedName

internal data class CategoryDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)