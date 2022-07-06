package com.eneselcuk.gameyuppiapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel

abstract class BaseFragment<dBinding : ViewDataBinding> : Fragment() {

    private var _binding: dBinding? = null
    val binding get() = _binding


    abstract fun getDataBinding(): dBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = getDataBinding()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        definition()
        setObserver()
        swipe()
        search()
        setData()
        setClick()
    }

    open fun definition() {}
    open fun setObserver() {}
    open fun swipe() {}
    open fun search() {}
    open fun setData() {}
    open fun setClick() {}

    protected abstract val progressBar : View

    protected fun setProgressStatus(isLoading: Boolean) {
        progressBar.isVisible = isLoading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}