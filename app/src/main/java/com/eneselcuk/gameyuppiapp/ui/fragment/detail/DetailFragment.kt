package com.eneselcuk.gameyuppiapp.ui.fragment.detail

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.eneselcuk.gameyuppiapp.R
import com.eneselcuk.gameyuppiapp.base.BaseFragment
import com.eneselcuk.gameyuppiapp.data.remote.model.deails.DeailsItem
import com.eneselcuk.gameyuppiapp.databinding.FragmentDetailBinding
import com.eneselcuk.gameyuppiapp.ui.fragment.detail.adapter.DetailAdapter
import com.eneselcuk.gameyuppiapp.util.Constants.defaultTagL
import com.eneselcuk.gameyuppiapp.util.Constants.defaultTagU
import com.eneselcuk.gameyuppiapp.util.Constants.tagL1
import com.eneselcuk.gameyuppiapp.util.Constants.tagL2
import com.eneselcuk.gameyuppiapp.util.Constants.tagL3
import com.eneselcuk.gameyuppiapp.util.Constants.tagL4
import com.eneselcuk.gameyuppiapp.util.Constants.tagL5
import com.eneselcuk.gameyuppiapp.util.Constants.tagL6
import com.eneselcuk.gameyuppiapp.util.Constants.tagL7
import com.eneselcuk.gameyuppiapp.util.Constants.tagU1
import com.eneselcuk.gameyuppiapp.util.Constants.tagU2
import com.eneselcuk.gameyuppiapp.util.Constants.tagU3
import com.eneselcuk.gameyuppiapp.util.Constants.tagU4
import com.eneselcuk.gameyuppiapp.util.Constants.tagU5
import com.eneselcuk.gameyuppiapp.util.Constants.tagU6
import com.eneselcuk.gameyuppiapp.util.Constants.tagU7
import com.eneselcuk.gameyuppiapp.util.searchV

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var detailAdapter: DetailAdapter

    private var activeNotActive: Boolean = false

    private val arraySearch = ArrayList<DeailsItem>()
    override fun getDataBinding() = FragmentDetailBinding.inflate(layoutInflater)

    override fun definition() {

        binding?.tag = this
        binding?.tagClick = this

        if (args.gameStores.storeID.isEmpty()) {
            Toast.makeText(requireContext(), "not stores", Toast.LENGTH_SHORT).show()
        }else {
            viewModel.getDeails(args.gameStores.storeID, defaultTagL, defaultTagU)
        }

        detailAdapter = DetailAdapter()
        binding?.recyclerView?.apply {
            this.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = detailAdapter
        }
        searchV(binding?.searchView, detailAdapter, arraySearch)
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun setObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.deailsFlow
                        .collect { detailUiState ->
                            detailUiState(detailUiState)
                        }
                }
            }
        }
    }

    override val progressBar: View
        get() = binding!!.progress

    private fun detailUiState(detailUiState: DetailsUiState) {
        detailUiState.loading?.let {
            setProgressStatus(it)
        }
        detailUiState.error.let { it }
        detailUiState.deails?.let {
            detailAdapter.submitList(it)
            arraySearch.addAll(it)
        }
    }

    fun initSearchTag1() {
        viewModel.getDeails(args.gameStores.storeID, tagL1, tagU1)
    }

    fun initSearchTag2() {
        viewModel.getDeails(args.gameStores.storeID, tagL2, tagU2)
    }

    fun initSearchTag3() {
        viewModel.getDeails(args.gameStores.storeID, tagL3, tagU3)
    }

    fun initSearchTag4() {
        viewModel.getDeails(args.gameStores.storeID, tagL4, tagU4)
    }

    fun initSearchTag5() {
        viewModel.getDeails(args.gameStores.storeID, tagL5, tagU5)
    }

    fun initSearchTag6() {
        viewModel.getDeails(args.gameStores.storeID, tagL6, tagU6)
    }

    fun initSearchTag7() {
        viewModel.getDeails(args.gameStores.storeID, tagL7, tagU7)
    }

    fun btnClick() {
        activeNotActive = if (activeNotActive) {
            binding?.visible = true
            binding?.btnPrice?.setImageResource(R.drawable.ic_baseline_arrow_drop_up)
            false
        } else {
            binding?.visible = false
            binding?.btnPrice?.setImageResource(R.drawable.ic_baseline_arrow_drop_down)
            true
        }
    }

    fun linerClick() {
        activeNotActive = if (activeNotActive) {
            binding?.visible = true
            binding?.btnPrice?.setImageResource(R.drawable.ic_baseline_arrow_drop_up)
            false
        } else {
            binding?.visible = false
            binding?.btnPrice?.setImageResource(R.drawable.ic_baseline_arrow_drop_down)
            true
        }
    }


}
