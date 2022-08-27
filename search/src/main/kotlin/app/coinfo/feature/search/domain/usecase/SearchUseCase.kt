package app.coinfo.feature.search.domain.usecase

import android.util.Log
import app.coinfo.feature.search.common.Mappers.toSearchResult
import app.coinfo.feature.search.common.Resource
import app.coinfo.feature.search.domain.model.SearchResult
import app.coinfo.feature.search.domain.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

internal class SearchUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    operator fun invoke(query: String): Flow<Resource<SearchResult>> = flow {
        try { // TODO: Change to runCatching
            Log.d(TAG, "Search Use Case")
            Log.d(TAG, "  > Query: $query")
            emit(Resource.Loading())
            val searchCoins = repository.search(query)
            Log.d(TAG, "  < Returned Coins Amount: ${searchCoins.coins.size}")
            if (searchCoins.coins.isNotEmpty()) {
                val searchCoinsDetails = repository.getMarketData(searchCoins.coins.map { it.id })
                val result = searchCoinsDetails.toSearchResult()
                emit(Resource.Success(result))
            } else {
                emit(Resource.Success(SearchResult(emptyList())))
            }
        } catch (e: HttpException) {
            Log.e(TAG, "Error occurs while searching the coins", e)
            emit(Resource.Failure(e.localizedMessage))
        } catch (e: IOException) {
            Log.e(TAG, "Error occurs while searching the coins", e)
            emit(Resource.Failure(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)

    companion object {
        private const val TAG = "SearchUseCase"
    }
}