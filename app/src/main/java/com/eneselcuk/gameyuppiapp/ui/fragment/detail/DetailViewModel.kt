package com.eneselcuk.gameyuppiapp.ui.fragment.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eneselcuk.gameyuppiapp.domain.repository.GameRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repos: GameRepos) : ViewModel() {

    private val _deailsFlow: MutableStateFlow<DetailsUiState> =
        MutableStateFlow(DetailsUiState(loading = true))
    val deailsFlow: StateFlow<DetailsUiState> = _deailsFlow

    private var job: Job? = null

    fun getDeails(storeId: String, lowerPrice: String? = null, upperPrice: String? = null) {
        job?.cancel()
        job = viewModelScope.launch {
            repos.deails(storeId, lowerPrice, upperPrice)
                .onStart {
                    _deailsFlow.update { dealsUiState ->
                        dealsUiState.copy(deails = null, loading = true)
                    }
                }
                .catch {
                    it.message.let { message ->
                        _deailsFlow.update { dealsUiState ->
                            dealsUiState.copy(
                                deails = null,
                                loading = false,
                                error = message
                            )
                        }
                    }
                }
                .collect {
                    it.data.let { deails ->
                        _deailsFlow.update { dealsUiState ->
                            dealsUiState.copy(deails = deails, loading = false, error = null)
                        }
                    }
                }
        }
    }


}