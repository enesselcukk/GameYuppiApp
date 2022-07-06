package com.eneselcuk.gameyuppiapp.ui.fragment.detail.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eneselcuk.gameyuppiapp.data.remote.model.deails.DeailsItem

object DetailDiffUtil : DiffUtil.ItemCallback<DeailsItem>() {
    override fun areItemsTheSame(oldItem: DeailsItem, newItem: DeailsItem): Boolean =
        oldItem.dealID == newItem.dealID

    override fun areContentsTheSame(oldItem: DeailsItem, newItem: DeailsItem): Boolean =
        oldItem == newItem

}