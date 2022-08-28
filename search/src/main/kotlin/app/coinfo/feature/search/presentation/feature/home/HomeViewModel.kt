package app.coinfo.feature.search.presentation.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.coinfo.feature.search.common.Resource
import app.coinfo.feature.search.data.local.SearchPreferences
import app.coinfo.feature.search.domain.model.RecentViewedResult
import app.coinfo.feature.search.domain.model.SearchResult
import app.coinfo.feature.search.domain.model.TrendingResult
import app.coinfo.feature.search.domain.usecase.GetRecentViewedCoinsUseCase
import app.coinfo.feature.search.domain.usecase.GetTrendingCoinsUseCase
import app.coinfo.feature.search.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getTrendingCoinsUseCase: GetTrendingCoinsUseCase,
    private val getRecentViewedCoinsUseCase: GetRecentViewedCoinsUseCase,
    private val searchUseCase: SearchUseCase,
    private val preferences: SearchPreferences
) : ViewModel() {

    private var searchJob: Job? = null

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
        viewModelScope.launch { preferences.addRecentViewedCoinId(id) }
    }

    fun onQueryTextSubmit(query: String?) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(WAIT_TIMEOUT_BEFORE_SEARCH)
            query?.let { getSearchedCoins(it) }
        }
    }

    fun onQueryTextChange(query: String?) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(WAIT_TIMEOUT_BEFORE_SEARCH)
            query?.let {
                if (it.isNotBlank() && it.isNotEmpty()) {
                    getSearchedCoins(it)
                } else {
                    _state.update { state ->
                        state.copy(
                            searchCoinsResult = SearchResult(emptyList()),
                            error = null
                        )
                    }
                }
            }
        }
    }

    private fun getSearchedCoins(query: String) {
        searchUseCase(query).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            searchCoinsResult = result.data ?: SearchResult(emptyList()),
                            error = null
                        )
                    }
                }
                is Resource.Failure -> {
                    _state.update {
                        it.copy(
                            searchCoinsResult = result.data ?: SearchResult(emptyList()),
                            error = result.message
                        )
                    }
                }
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getTrendingCoins() {
        getTrendingCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            trendingCoinsResult = result.data ?: TrendingResult(emptyList()),
                            error = ""
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
                            recentViewedCoinsResult = result.data
                                ?: RecentViewedResult(emptyList()),
                            error = ""
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

    companion object {
        private const val WAIT_TIMEOUT_BEFORE_SEARCH = 500L
    }
}