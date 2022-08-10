package app.coinfo.feature.search.domain.usecase

import app.coinfo.feature.search.common.Resource
import app.coinfo.feature.search.domain.model.SearchUI
import app.coinfo.feature.search.domain.repository.SearchRepository
import app.coinfo.feature.search.mapper.toSearchUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

internal class SearchUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    operator fun invoke(query: String): Flow<Resource<SearchUI>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.search(query).toSearchUI()
            emit(Resource.Success(result))
        } catch (e: HttpException) {
            emit(Resource.Failure(e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Failure(e.localizedMessage))
        }
    }
}