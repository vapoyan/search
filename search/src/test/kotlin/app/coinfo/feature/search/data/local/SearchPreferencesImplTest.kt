package app.coinfo.feature.search.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.common.truth.Truth.assertThat
import io.mockk.called
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class SearchPreferencesImplTest {

    private val mockedRecentCoinsPreferences: SharedPreferences = mockk(relaxed = true)
    private val mockedContext: Context = mockk(relaxed = true) {
        every {
            getSharedPreferences(
                "recent_coins.pref",
                Context.MODE_PRIVATE
            )
        } returns mockedRecentCoinsPreferences
    }

    private val objectUnderTest = SearchPreferencesImpl(mockedContext)

    @Test
    fun `id successfully added if no previously saved ids`() = runBlocking {
        every { mockedRecentCoinsPreferences.all } returns emptyMap()
        objectUnderTest.addRecentViewedCoinId("ripple")

        coVerify { mockedRecentCoinsPreferences.edit().putLong("ripple", any()) }
        coVerify { mockedRecentCoinsPreferences.edit().remove("ripple") wasNot called }
    }

    @Test
    fun `when adding more ids then maximum allowed, the latest added id removed`() = runBlocking {
        every { mockedRecentCoinsPreferences.all } returns mapOf(
            "btc1" to 1L, "btc2" to 2L, "btc3" to 3L, "btc4" to 4L, "btc5" to 5L,
            "btc6" to 6L, "btc7" to 7L, "btc8" to 8L, "btc9" to 9L, "btc10" to 10L
        )

        objectUnderTest.addRecentViewedCoinId("bitcoin")

        coVerify { mockedRecentCoinsPreferences.edit().remove("btc1") }
        coVerify { mockedRecentCoinsPreferences.edit().putLong("bitcoin", any()) }
    }

    @Test
    fun aaa() = runBlocking {
        every { mockedRecentCoinsPreferences.all } returns mapOf(
            "btc9" to 9L, "btc2" to 2L, "btc3" to 3L, "btc4" to 4L, "btc5" to 5L, "btc1" to 1L,
            "btc6" to 6L, "btc8" to 8L, "btc10" to 10L, "btc7" to 7L
        )

        val result = objectUnderTest.getRecentViewedCoinIds()

        assertThat(result).containsExactly(
            "btc10", "btc9", "btc8", "btc7", "btc6", "btc5", "btc4", "btc3", "btc2", "btc1"
        ).inOrder()
    }
}