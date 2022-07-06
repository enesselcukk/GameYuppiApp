package com.eneselcuk.gameyuppiapp.ui.fragment.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.eneselcuk.gameyuppiapp.data.remote.model.deails.DeailsItem
import com.eneselcuk.gameyuppiapp.databinding.ViewItemDetailBinding

class DetailVHolder(private val binding: ViewItemDetailBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        games: DeailsItem,
//        click: DetailAdapter.GameClick
    ) {
        binding.deailDeata = games
    }
}