package com.eneselcuk.gameyuppiapp.data.remote.repos


import com.eneselcuk.gameyuppiapp.data.remote.model.deails.Deails
import com.eneselcuk.gameyuppiapp.data.remote.model.stores.Stores
import com.eneselcuk.gameyuppiapp.data.remote.service.GameService
import com.eneselcuk.gameyuppiapp.domain.repository.GameRepos
import com.eneselcuk.gameyuppiapp.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val api: GameService,
) : GameRepos {
    override suspend fun getStories(): Flow<NetworkResult<Stores>> = flow {
        val result: NetworkResult<Stores> =
            try {
                val data = api.getStories()
                NetworkResult.Success(data)
            } catch (ex: Throwable) {
                NetworkResult.Error(ex.message, null)

            }
        emit(result)
    }.flowOn(Dispatchers.IO)

    override suspend fun deails(
        storeID: String,
        lowerPrice: String?,
        upperPrice: String?
    ): Flow<NetworkResult<Deails>> =
        flow {
            val result: NetworkResult<Deails> =
                try {
                    val data = api.getDeals(storeID, lowerPrice, upperPrice)
                    NetworkResult.Success(data)
                } catch (ex: Throwable) {
                    NetworkResult.Error(ex.message, null)
                }
            emit(result)
        }.flowOn(Dispatchers.IO)
}