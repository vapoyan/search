package app.coinfo.feature.search.domain.usecase

import app.coinfo.feature.search.common.Resource
import app.coinfo.feature.search.domain.model.SearchResult
import app.coinfo.feature.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class GetTrendingCoinsUseCase  @Inject constructor(
    private val repository: SearchRepository
) {
    operator fun invoke(): Flow<Resource<SearchResult>> = flow {
    }

}
