package com.eneselcuk.gameyuppiapp.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.eneselcuk.gameyuppiapp.R
import com.eneselcuk.gameyuppiapp.data.remote.model.stores.StoresItem

class HomeGamesAdapter(private val click: GameClick) :
    ListAdapter<StoresItem, HomeGamesVHolder>(DiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeGamesVHolder =
        HomeGamesVHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.view_item_games, parent, false
            )
        )

    override fun onBindViewHolder(holder: HomeGamesVHolder, position: Int) {
        val gamePosition = getItem(position)
        holder.bind(gamePosition, click)
    }

    class GameClick(val click: (StoresItem) -> Unit) {
        fun onClick(game: StoresItem) = click(game)
    }
}