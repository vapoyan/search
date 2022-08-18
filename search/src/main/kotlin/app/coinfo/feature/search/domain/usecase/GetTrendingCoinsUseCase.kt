package app.coinfo.feature.search.domain.usecase

import app.coinfo.feature.search.common.Mappers.toTrendingResult
import app.coinfo.feature.search.common.Resource
import app.coinfo.feature.search.domain.model.TrendingResult
import app.coinfo.feature.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

internal class GetTrendingCoinsUseCase  @Inject constructor(
    private val repository: SearchRepository
) {
    operator fun invoke(): Flow<Resource<TrendingResult>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.trending().toTrendingResult()
            emit(Resource.Success(result))
        } catch (e: HttpException) {
            emit(Resource.Failure(e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Failure(e.localizedMessage))
        }
    }
}
