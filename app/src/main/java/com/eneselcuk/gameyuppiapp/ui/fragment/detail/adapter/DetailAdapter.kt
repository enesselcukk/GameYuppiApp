package com.eneselcuk.gameyuppiapp.ui.fragment.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.eneselcuk.gameyuppiapp.R
import com.eneselcuk.gameyuppiapp.data.remote.model.deails.DeailsItem

class DetailAdapter() :
    ListAdapter<DeailsItem, DetailVHolder>(DetailDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailVHolder =
        DetailVHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.view_item_detail, parent, false
            )
        )

    override fun onBindViewHolder(holder: DetailVHolder, position: Int) {
        val gamePosition = getItem(position)
        holder.bind(gamePosition)
    }

//    class GameClick(val click: (StoresItem) -> Unit) {
//        fun onClick(game: StoresItem) = click(game)
//    }
}