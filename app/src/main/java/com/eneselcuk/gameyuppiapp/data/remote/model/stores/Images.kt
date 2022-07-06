package com.eneselcuk.gameyuppiapp.data.remote.model.stores

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Images(
    val banner: String,
    val icon: String,
    val logo: String
) : Parcelable