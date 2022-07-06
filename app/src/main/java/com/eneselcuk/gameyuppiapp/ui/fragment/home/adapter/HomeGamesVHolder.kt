package com.eneselcuk.gameyuppiapp.ui.fragment.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.eneselcuk.gameyuppiapp.data.remote.model.stores.StoresItem
import com.eneselcuk.gameyuppiapp.databinding.ViewItemGamesBinding

class HomeGamesVHolder(private val binding: ViewItemGamesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        games: StoresItem,
        click: HomeGamesAdapter.GameClick
    ) {
        binding.gameData = games
        binding.gameClick = click
    }
}