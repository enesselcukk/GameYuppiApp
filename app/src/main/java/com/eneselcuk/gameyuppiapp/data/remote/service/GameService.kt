package com.eneselcuk.gameyuppiapp.data.remote.service

import com.eneselcuk.gameyuppiapp.data.remote.model.deails.Deails
import com.eneselcuk.gameyuppiapp.data.remote.model.stores.Stores
import retrofit2.http.GET
import retrofit2.http.Query

interface GameService {
    @GET("stores")
    suspend fun getStories(): Stores

    @GET("deals")
    suspend fun getDeals(
        @Query("storeID") storeID: String,
        @Query("lowerPrice") lowerPrice: String? = null,
        @Query("upperPrice") upperPrice: String? = null
    ): Deails


}