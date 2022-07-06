package com.eneselcuk.gameyuppiapp.ui.fragment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eneselcuk.gameyuppiapp.domain.repository.GameRepos

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: GameRepos) : ViewModel() {

    private val _flowGame: MutableStateFlow<HomeUiState> =
        MutableStateFlow(HomeUiState(isLoading = true))
    val flowGame: StateFlow<HomeUiState> get() = _flowGame

    private var job: Job? = null

    fun getAllGame() {
        job?.cancel()
        job = viewModelScope.launch {
            repository.getStories()
                .onStart {
                    _flowGame.update { homeUiState ->
                        homeUiState.copy(isLoading = true)
                    }
                }
                .catch {
                    it.message?.let { error ->
                        _flowGame.update { homeUiState ->
                            homeUiState.copy(isLoading = null, error = error, stores = null)
                        }
                    }
                }
                .collect {
                    it.data.let { stores ->
                        _flowGame.update { homeUiState ->
                            homeUiState.copy(isLoading = false, stores = stores)
                        }
                    }
                }
        }
    }
}