package com.eneselcuk.gameyuppiapp.util

import android.app.Activity
import android.os.Build
import android.view.WindowInsets
import android.view.WindowManager

fun fullScreen(activity: Activity) {
    @Suppress("DEPRECATION")
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        activity.window.insetsController?.hide(WindowInsets.Type.statusBars())
    } else {
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}