package com.eneselcuk.gameyuppiapp.domain.repository

import com.eneselcuk.gameyuppiapp.data.remote.model.deails.Deails
import com.eneselcuk.gameyuppiapp.data.remote.model.stores.Stores
import com.eneselcuk.gameyuppiapp.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface GameRepos {
    suspend fun getStories(): Flow<NetworkResult<Stores>>
    suspend fun deails(
        storeID: String,
        lowerPrice: String? = null,
        upperPrice: String? = null
    ): Flow<NetworkResult<Deails>>
}