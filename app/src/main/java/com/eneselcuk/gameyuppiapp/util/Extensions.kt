package com.eneselcuk.gameyuppiapp.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.eneselcuk.gameyuppiapp.BuildConfig


@BindingAdapter("app:gameImage")
fun ImageView.setImageUrl(imageIcon: String?) {
    Glide.with(context)
        .load(imageIcon)
        .fitCenter()
        .into(this)
}

@BindingAdapter("app:storeImage")
fun ImageView.setStoreUrl(imageIcon: String?) {
    Glide.with(context)
        .load(BuildConfig.BASE_URL_IMAGE + imageIcon)
        .fitCenter()
        .into(this)
}


@BindingAdapter("app:progressbar")
fun ProgressBar.setProgress(visible: Boolean) {
    this.progress = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("app:priceCategoires")
fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}
