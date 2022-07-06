package com.eneselcuk.gameyuppiapp.ui.activity.login


import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.eneselcuk.gameyuppiapp.R
import com.eneselcuk.gameyuppiapp.base.BaseActivity
import com.eneselcuk.gameyuppiapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun definition() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragmentt) as NavHostFragment
        val navController = navHostFragment.navController
        activityDataBinding.navView.setupWithNavController(navController)
    }

}