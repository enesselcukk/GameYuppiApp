package com.eneselcuk.gameyuppiapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.eneselcuk.gameyuppiapp.base.BaseActivity
import com.eneselcuk.gameyuppiapp.databinding.ActivityMainBinding
import com.eneselcuk.gameyuppiapp.util.fullScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun definition() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragmentt) as NavHostFragment
        val navController = navHostFragment.navController
        activityDataBinding.navView.setupWithNavController(navController)

       // fullScreen(this@MainActivity)
    }

}