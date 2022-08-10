package app.coinfo.feature.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.coinfo.feature.search.common.Resource
import app.coinfo.feature.search.domain.model.SearchUI
import app.coinfo.feature.search.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _state = MutableLiveData<SearchState>()
    val state: LiveData<SearchState> = _state

    fun search(queue: String) {
        searchUseCase(queue).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = SearchState(result = result.data ?: SearchUI(emptyList()))
                }
                is Resource.Failure -> {
                    _state.value = SearchState(
                        error = result.message ?: "Error occurs while search"
                    )
                }
                is Resource.Loading -> {
                    _state.value = SearchState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}