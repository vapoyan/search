package app.coinfo.feature.search.domain.usecase

import android.util.Log
import app.coinfo.feature.search.common.Mappers.toRecentViewedResult
import app.coinfo.feature.search.common.Resource
import app.coinfo.feature.search.data.local.SearchPreferences
import app.coinfo.feature.search.domain.model.RecentViewedResult
import app.coinfo.feature.search.domain.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

internal class GetRecentViewedCoinsUseCase @Inject constructor(
    private val preferences: SearchPreferences,
    private val repository: SearchRepository,
) {
    operator fun invoke(): Flow<Resource<RecentViewedResult>> = flow {
        try {
            Log.d(TAG, "Get Recent Viewed Coins Use Case")
            emit(Resource.Loading())
            val ids = preferences.getRecentViewedCoinIds()
            Log.d("TAG", "  > IDs: $ids")
            emit(Resource.Success(repository.getMarketData(ids).toRecentViewedResult(ids))
                .also { Log.d(TAG, "  < Coins: ${it.data}") })
        } catch (e: HttpException) {
            Log.e(TAG, "Error occurs while loading coin information", e)
            emit(Resource.Failure(e.localizedMessage))
        } catch (e: IOException) {
            Log.e(TAG, "Error occurs while loading coin information", e)
            emit(Resource.Failure(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)

    companion object {
        private const val TAG = "GetRecentViewedCoins"
    }
}