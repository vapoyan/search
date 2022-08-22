package app.coinfo.feature.search.data.local

internal interface SearchPreferences {

    suspend fun addRecentViewedCoinId(id: String)

    suspend fun getRecentViewedCoinIds(): List<String>
}