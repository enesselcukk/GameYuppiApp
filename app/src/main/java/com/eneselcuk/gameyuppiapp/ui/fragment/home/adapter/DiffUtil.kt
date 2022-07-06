package com.eneselcuk.gameyuppiapp.ui.fragment.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eneselcuk.gameyuppiapp.data.remote.model.stores.StoresItem

object DiffUtil : DiffUtil.ItemCallback<StoresItem>() {
    override fun areItemsTheSame(oldItem: StoresItem, newItem: StoresItem): Boolean =
        oldItem.storeID == newItem.storeID
    override fun areContentsTheSame(oldItem: StoresItem, newItem: StoresItem): Boolean =
        oldItem == newItem
}