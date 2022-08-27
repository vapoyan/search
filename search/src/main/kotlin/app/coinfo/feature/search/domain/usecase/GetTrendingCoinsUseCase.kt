package app.coinfo.feature.search.domain.usecase

import android.util.Log
import app.coinfo.feature.search.common.Mappers.toTrendingResult
import app.coinfo.feature.search.common.Resource
import app.coinfo.feature.search.domain.model.TrendingResult
import app.coinfo.feature.search.domain.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * The use case for getting trending coins.
 * The use case will return a [Flow] of [Resource]. Every collector initially will receive
 * [Resource.Loading], after if the data received from the server [Resource.Success] will be
 * emitted; otherwise in case of error [Resource.Failure].
 *
 * The [Flow] will be executed in [Dispatchers.IO] context and emit the result in the context of
 * the original collector.
 */
internal class GetTrendingCoinsUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    operator fun invoke(): Flow<Resource<TrendingResult>> = flow {
        try { // TODO: Change to runCatching
            emit(Resource.Loading())
            val trendingCoins = repository.trending()
            val coin = repository.getPrice(trendingCoins.coins.map { it.item.id })
            emit(Resource.Success(trendingCoins.toTrendingResult(coin)))
        } catch (e: HttpException) {
            Log.e(TAG, "Exception occurs while Getting List of Trending coins", e)
            emit(Resource.Failure(e.localizedMessage))
        } catch (e: IOException) {
            Log.e(TAG, "Exception occurs while Getting List of Trending coins", e)
            emit(Resource.Failure(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)

    companion object {
        private const val TAG = "GetTrendingCoinsUseCase"
    }
}
