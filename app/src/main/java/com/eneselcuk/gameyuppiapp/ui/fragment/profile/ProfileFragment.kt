package com.eneselcuk.gameyuppiapp.ui.fragment.profile

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.eneselcuk.gameyuppiapp.R
import com.eneselcuk.gameyuppiapp.base.BaseFragment
import com.eneselcuk.gameyuppiapp.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    private val viewModel: ProfileViewModel by lazy {
        ProfileViewModel(requireActivity())
    }
    private lateinit var animation: Animation

    override fun getDataBinding() = FragmentProfileBinding.inflate(layoutInflater)

    override fun definition() {
        viewModel.getUser()
        binding?.click = viewModel
        binding?.editClick = this
    }

    fun animation(idAnim: Int): Animation {
        animation = AnimationUtils.loadAnimation(requireContext(), idAnim)
        return animation
    }

    override fun setObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.userFlow
                        .collect {
                            binding?.setDataProfile = it
                        }
                }
            }
        }
    }

    fun editTextClick() {
        binding?.editStatus = true
        binding?.btnLogin?.visibility = View.GONE
        binding?.btnUpdate?.visibility = View.VISIBLE

        binding?.btnLogin?.startAnimation(animation(R.anim.btn_anim_out))
        binding?.btnUpdate?.startAnimation(animation(R.anim.btn_anim_enter))
    }

    override val progressBar: View
        get() = TODO("Not yet implemented")


}