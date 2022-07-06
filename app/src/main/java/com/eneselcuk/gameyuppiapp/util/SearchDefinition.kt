package com.eneselcuk.gameyuppiapp.util

import androidx.appcompat.widget.SearchView
import com.eneselcuk.gameyuppiapp.data.remote.model.deails.DeailsItem
import com.eneselcuk.gameyuppiapp.ui.fragment.detail.adapter.DetailAdapter


fun searchV(
    searchView: SearchView? = null,
    detailAdapter: DetailAdapter,
    list: ArrayList<DeailsItem>
) {
    searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            val arrayListSearch = ArrayList<DeailsItem>()
            list.filter { search ->
                if (newText?.let {
                        search.title.lowercase().contains(it.lowercase())
                    } == true) {
                    arrayListSearch.add(search)
                }
                true
            }
            detailAdapter.submitList(arrayListSearch)
            return false
        }
    })
}



