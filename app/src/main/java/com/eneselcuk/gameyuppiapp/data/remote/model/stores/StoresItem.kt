package com.eneselcuk.gameyuppiapp.data.remote.model.stores

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoresItem(
    val images: Images,
    val isActive: Int,
    val storeID: String,
    val storeName: String
) : Parcelable