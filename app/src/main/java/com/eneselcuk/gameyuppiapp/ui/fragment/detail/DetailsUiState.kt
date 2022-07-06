package com.eneselcuk.gameyuppiapp.ui.fragment.detail

import com.eneselcuk.gameyuppiapp.data.remote.model.deails.Deails

data class DetailsUiState(
     val deails: Deails? = null,
     val loading: Boolean? = false,
     val error: String? = null
)