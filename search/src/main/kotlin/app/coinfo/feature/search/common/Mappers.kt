package app.coinfo.feature.search.common

import app.coinfo.feature.search.data.remote.dto.CoinDto
import app.coinfo.feature.search.data.remote.dto.SearchResultDto
import app.coinfo.feature.search.domain.model.Coin
import app.coinfo.feature.search.domain.model.SearchResult

internal object Mappers {

    private fun CoinDto.toCoin() = Coin(
        id = id,
        large = large,
        marketCapRank = marketCapRank,
        name = name,
        symbol = symbol,
        thumb = thumb,
    )

    fun SearchResultDto.toSearchResult() = SearchResult(
        coins = coins.map { it.toCoin() }
    )

}