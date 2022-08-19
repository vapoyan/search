package app.coinfo.feature.search.domain.usecase

import app.coinfo.feature.search.common.Resource
import app.coinfo.feature.search.data.remote.dto.TrendingResultDto
import app.coinfo.feature.search.domain.model.TrendingCoin
import app.coinfo.feature.search.domain.repository.SearchRepository
import app.coinfo.test.Helpers.readFile
import com.beust.klaxon.Klaxon
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.HttpException
import java.io.IOException


internal class GetTrendingCoinsUseCaseTest {

    private val mockedRepository: SearchRepository = mockk(relaxed = true)

    private val objectUnderTest = GetTrendingCoinsUseCase(mockedRepository)

    @Test
    fun `trending coins returned successfully`() = runBlocking {
        val json = readFile("response_trending_coins_success.json")

        // Repository returns list of trending coins
        Klaxon().parse<TrendingResultDto>(json)?.let { response ->
            coEvery { mockedRepository.trending() } returns response
        }

        // First Loading is emitted
        assertThat(objectUnderTest().first()).isInstanceOf(Resource.Loading::class.java)

        // Second List of Trending Coins emitted
        val response = objectUnderTest().last()
        assertThat(response).isInstanceOf(Resource.Success::class.java)
        assertThat(response.data).isNotNull()

        val coins = response.data!!.coins
        assertThat(coins).isNotEmpty()
        assertThat(coins.size).isEqualTo(7)
        assertThat(coins).contains(
            TrendingCoin(
                id = "stargate-finance",
                coinId = 24413,
                name = "Stargate Finance",
                symbol = "STG",
                marketCapRank = 292,
                thumb = "https://assets.coingecko.com/coins/images/24413/thumb/STG_LOGO.png?1647654518",
                small = "https://assets.coingecko.com/coins/images/24413/small/STG_LOGO.png?1647654518",
                large = "https://assets.coingecko.com/coins/images/24413/large/STG_LOGO.png?1647654518",
                slug = "stargate-finance",
                priceBtc = 0.000030361599060620554,
                score = 1
            )
        )
    }

    @Test
    fun `HTTP exception occurs while requesting trending coins`() = runBlocking {
        coEvery { mockedRepository.trending() } throws HttpException(mockk(relaxed = true) {
            every { message() } returns "HTTP Exception"
        })

        // First Loading is emitted
        assertThat(objectUnderTest().first()).isInstanceOf(Resource.Loading::class.java)

        // Second Error
        val response = objectUnderTest().last()
        assertThat(response).isInstanceOf(Resource.Failure::class.java)
        assertThat(response.message).isNotNull()
        assertThat(response.message).isNotEmpty()
        assertThat(response.message).isEqualTo("HTTP 0 HTTP Exception")
    }

    @Test
    fun `IO exception occurs while requesting trending coins`() = runBlocking {
        coEvery { mockedRepository.trending() } throws IOException("IO Exception")

        // First Loading is emitted
        assertThat(objectUnderTest().first()).isInstanceOf(Resource.Loading::class.java)

        // Second Error
        val response = objectUnderTest().last()
        assertThat(response).isInstanceOf(Resource.Failure::class.java)
        assertThat(response.message).isNotNull()
        assertThat(response.message).isNotEmpty()
        assertThat(response.message).isEqualTo("IO Exception")
    }
}