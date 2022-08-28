package app.coinfo.feature.search.domain.usecase

import app.coinfo.feature.search.common.Resource
import app.coinfo.feature.search.data.remote.dto.MarketDataResultDto
import app.coinfo.feature.search.data.remote.dto.SearchResultDto
import app.coinfo.feature.search.domain.model.SearchCoin
import app.coinfo.feature.search.domain.repository.SearchRepository
import app.coinfo.test.Helpers
import com.google.common.truth.Truth
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.HttpException
import java.io.IOException


internal class SearchUseCaseTest {

    private val mockedRepository: SearchRepository = mockk(relaxed = true)

    private val objectUnderTest = SearchUseCase(mockedRepository)

    @Test
    fun `when user search for 'bitcoin' list of 25 coins is returned`() = runBlocking {
        val searchBitcoin = Helpers.readFile("response_search_coin_query_bitcoin.json")
        // Mock search result response for query "bitcoin".
        Gson().fromJson(searchBitcoin, SearchResultDto::class.java).let {
            coEvery { mockedRepository.search("bitcoin") } returns it
        }

        val searchBitcoinMarketData =
            Helpers.readFile("response_search_coin_query_bitcoin_market_data.json")
        // Mock market date details response for query "bitcoin".
        Gson().fromJson(searchBitcoinMarketData, MarketDataResultDto::class.java)?.let {
            coEvery { mockedRepository.getMarketData(any()) } returns it
        }

        // First Loading is emitted
        Truth.assertThat(objectUnderTest("bitcoin").first())
            .isInstanceOf(Resource.Loading::class.java)

        // Second List of Trending Coins emitted
        val response = objectUnderTest("bitcoin").last()
        Truth.assertThat(response).isInstanceOf(Resource.Success::class.java)
        Truth.assertThat(response.data).isNotNull()

        val coins = response.data!!.coins
        Truth.assertThat(coins.size).isEqualTo(25)
        Truth.assertThat(coins[0]).isEqualTo(
            SearchCoin(
                id = "bitcoin",
                name = "Bitcoin",
                symbol = "btc",
                imageUrl = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
                price = 20032.0,
                priceChangePercentage24h = -0.23825
            )
        )
        Truth.assertThat(coins[24]).isEqualTo(
            SearchCoin(
                id = "bitcoinx",
                name = "BitcoinX",
                symbol = "bcx",
                imageUrl = "https://assets.coingecko.com/coins/images/1351/large/bitcoinx.png?1547035376",
                price = 0.00028054,
                priceChangePercentage24h = -0.12693
            )
        )
    }

    @Test
    fun `when user search for 'abcdefgh' which returns no results`() = runBlocking {
        val searchAbcdefgh = Helpers.readFile("response_search_coin_no_results.json")
        // Mock search result response for query "abcdefgh".
        Gson().fromJson(searchAbcdefgh, SearchResultDto::class.java).let {
            coEvery { mockedRepository.search("abcdefgh") } returns it
        }

        // First Loading is emitted
        Truth.assertThat(objectUnderTest("abcdefgh").first())
            .isInstanceOf(Resource.Loading::class.java)

        val response = objectUnderTest("abcdefgh").last()
        Truth.assertThat(response).isInstanceOf(Resource.Failure::class.java)
        Truth.assertThat(response.data).isNull()
        Truth.assertThat(response.message).isEqualTo("No results found for \"abcdefgh\"")
    }

    @Test
    fun `when user search for 'abc' server returns 429 Too Many Requests`() = runBlocking {
        coEvery { mockedRepository.search("abc") } throws HttpException(mockk() {
            every { message() } returns "Too Many Requests"
            every { code() } returns 429
        })

        // First Loading is emitted
        Truth.assertThat(objectUnderTest("abc").first()).isInstanceOf(Resource.Loading::class.java)

        val response = objectUnderTest("abc").last()
        Truth.assertThat(response).isInstanceOf(Resource.Failure::class.java)
        Truth.assertThat(response.data).isNull()
        Truth.assertThat(response.message).isEqualTo("HTTP 429 Too Many Requests")
    }

    @Test
    fun `when user search for 'hello' an IO exception is thrown`() = runBlocking {
        coEvery { mockedRepository.search("hello") } throws IOException("Can't read data fom stream")

        // First Loading is emitted
        Truth.assertThat(objectUnderTest("hello").first())
            .isInstanceOf(Resource.Loading::class.java)

        val response = objectUnderTest("hello").last()
        Truth.assertThat(response).isInstanceOf(Resource.Failure::class.java)
        Truth.assertThat(response.data).isNull()
        Truth.assertThat(response.message).isEqualTo("Can't read data fom stream")
    }

}