package app.coinfo.feature.search.mapper

import app.coinfo.feature.search.data.remote.dto.CoinDto
import app.coinfo.feature.search.data.remote.dto.SearchDto
import app.coinfo.feature.search.domain.model.CoinUI
import app.coinfo.feature.search.domain.model.SearchUI

internal fun CoinDto.toCoinUI() = CoinUI(
    id = id,
    large = large,
    marketCapRank = marketCapRank,
    name = name,
    symbol = symbol,
    thumb = thumb,
)

internal fun SearchDto.toSearchUI() = SearchUI(
    coins = coins.map { it.toCoinUI() }
)