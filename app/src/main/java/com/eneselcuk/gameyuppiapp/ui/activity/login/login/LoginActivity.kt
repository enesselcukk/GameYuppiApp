package com.eneselcuk.gameyuppiapp.ui.activity.login.login


import android.content.Intent
import com.eneselcuk.gameyuppiapp.ui.activity.login.MainActivity
import com.eneselcuk.gameyuppiapp.base.BaseActivity
import com.eneselcuk.gameyuppiapp.databinding.ActivityLoginBinding
import com.eneselcuk.gameyuppiapp.ui.activity.login.signUp.SignUpActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private val viewModel: LoginViewModel by lazy {
        LoginViewModel(this@LoginActivity)
    }

    override fun definition() {


        val db = Firebase.auth.currentUser

        if (db != null) {
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }

        activityDataBinding.setDataAndClick = this
        activityDataBinding.setData = viewModel

    }


    fun signUp() {
        val myIntent = Intent(this@LoginActivity, SignUpActivity::class.java)
        startActivity(myIntent)
    }

}

