package app.coinfo.feature.search.data.remote.dto

internal data class SearchResultDto(
    val categories: List<CategoryDto>,
    val coins: List<CoinDto>,
    val exchanges: List<ExchangeDto>,
    val icos: List<Any>,
    val nfts: List<NftDto>
)