package com.eneselcuk.gameyuppiapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.ViewDataBinding
import com.eneselcuk.gameyuppiapp.util.fullScreen

abstract class BaseActivity<VB : ViewDataBinding>(
    private val inflateLayout: (LayoutInflater) -> VB
) : AppCompatActivity() {

    protected lateinit var activityDataBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDataBinding = inflateLayout(layoutInflater)
        definition()
        setContentView(activityDataBinding.root)

        fullScreen(this)
    }

    open fun definition() {}
    open fun setObserver() {}


}