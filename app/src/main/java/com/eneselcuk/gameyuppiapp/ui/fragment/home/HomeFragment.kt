package com.eneselcuk.gameyuppiapp.ui.fragment.home


import android.annotation.SuppressLint
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.eneselcuk.gameyuppiapp.base.BaseFragment
import com.eneselcuk.gameyuppiapp.databinding.FragmentHomeBinding
import com.eneselcuk.gameyuppiapp.ui.fragment.home.adapter.HomeGamesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getDataBinding() = FragmentHomeBinding.inflate(layoutInflater)
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeGamesAdapter

    override fun definition() {
        homeAdapter = HomeGamesAdapter(HomeGamesAdapter.GameClick {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(action)
        })
        viewModel.getAllGame()

        binding?.rcyclerView.apply {
            this?.layoutManager =
                GridLayoutManager(requireContext(), 3)
            this?.adapter = homeAdapter
        }
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun setObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.flowGame.collect {
                        homeUiStatus(it)
                    }
                }
            }
        }
    }

    private fun homeUiStatus(homeUiState: HomeUiState) {
        homeUiState.isLoading?.let { setProgressStatus(it) }
        homeUiState.error.let { it }
        homeUiState.stores?.let {
            homeAdapter.submitList(it)
        }
    }
    override val progressBar: View
        get() = binding!!.progress
}