package com.eneselcuk.gameyuppiapp.ui.fragment.home

import com.eneselcuk.gameyuppiapp.data.remote.model.stores.Stores

data class HomeUiState(
    val isLoading: Boolean? = false,
    val error: String? = null,
    val stores: Stores? = null,
)