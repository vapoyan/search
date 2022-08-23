package app.coinfo.feature.search.data.local

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class SearchPreferencesImpl(
    appContext: Context
) : SearchPreferences {

    private val recentCoinsPreferences =
        appContext.getSharedPreferences(RECENT_COINS_PREFERENCES, Context.MODE_PRIVATE)

    override suspend fun addRecentViewedCoinId(id: String) = withContext(Dispatchers.IO) {
        Log.d(TAG, "Add Recent Viewed Coin ID")
        Log.d(TAG, "  > ID: $id")
        if (recentCoinsPreferences.all.size >= RECENT_COINS_MAX_AMOUNT) {
            Log.d(TAG, "  > Maximum amount of recent coins received, removing last coin id")
            with(recentCoinsPreferences.all.entries.minByOrNull { it.value as Long }) {
                this@with?.key?.let { k -> recentCoinsPreferences.edit(commit = true) { remove(k) } }
            }
        }
        recentCoinsPreferences.edit(commit = true) { putLong(id, System.currentTimeMillis()) }
        with(recentCoinsPreferences) {
            Log.d(TAG, "  < Total amount of recent coin IDs: ${all.size}")
            Log.d(TAG, "  < List of IDs: ${all.keys}")
            Unit
        }
    }

    override suspend fun getRecentViewedCoinIds() = withContext(Dispatchers.IO) {
        Log.d(TAG, "Get Recent Viewed Coin IDs")
        recentCoinsPreferences.all.entries.sortedByDescending { it.value as Long }.map { it.key }
            .also {
                Log.d(TAG, "  < IDs: $it")
            }
    }


    companion object {
        private const val TAG = "SearchPreferencesImpl"
        private const val RECENT_COINS_MAX_AMOUNT = 10
        private const val RECENT_COINS_PREFERENCES = "recent_coins.pref"
    }
}