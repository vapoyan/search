package app.coinfo.feature.search.presentation.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.coinfo.feature.search.common.Resource
import app.coinfo.feature.search.data.local.SearchPreferences
import app.coinfo.feature.search.domain.model.RecentViewedResult
import app.coinfo.feature.search.domain.model.TrendingResult
import app.coinfo.feature.search.domain.usecase.GetRecentViewedCoinsUseCase
import app.coinfo.feature.search.domain.usecase.GetTrendingCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getTrendingCoinsUseCase: GetTrendingCoinsUseCase,
    private val getRecentViewedCoinsUseCase: GetRecentViewedCoinsUseCase,
    private val preferences: SearchPreferences
) : ViewModel() {

    private val initialState = HomeState()

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    init {
        getTrendingCoins()
        getRecentViewedCoins()
    }

    fun onTrendingCoinClicked(id: String) {
        viewModelScope.launch { preferences.addRecentViewedCoinId(id) }
    }

    fun onRecentViewedCoinClicked(id: String) {

    }

    fun onSearchResultClicked(id: String) {

    }

    private fun getTrendingCoins() {
        getTrendingCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            trendingCoinsResult = result.data ?: TrendingResult(
                                emptyList()
                            )
                        )
                    }
                }
                is Resource.Failure -> {
                    _state.update { it.copy(error = result.message ?: "Error occurs while search") }
                }
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getRecentViewedCoins() {
        getRecentViewedCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            recentViewedCoinsResult = result.data ?: RecentViewedResult(emptyList())
                        )
                    }
                }
                is Resource.Failure -> {
                    _state.update { it.copy(error = result.message ?: "Error occurs while search") }
                }
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
            }
        }.launchIn(viewModelScope)
    }
}