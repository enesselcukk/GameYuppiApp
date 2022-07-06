package com.eneselcuk.gameyuppiapp.ui.activity.login.login

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import com.eneselcuk.gameyuppiapp.MainActivity
import com.eneselcuk.gameyuppiapp.util.Constants.TAG
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.*


class LoginViewModel(
    private val context: Activity
) : ViewModel() {
    private var fAuth = Firebase.auth

    fun signIn(email: String, password: String) {
        if (email.isEmpty() && password.isEmpty()) {
            Log.d(TAG, "LoginUser : null")
        } else {
            fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "LoginUser WithEmail:success")
                    click()
                } else {
                    Log.d(ContentValues.TAG, "LoginUser  : error")
                    Log.d(TAG, "LoginUser : null")
                }
            }
        }
    }

    private fun click() {
        val myIntent = Intent(context, MainActivity::class.java)
        context.finish()
        context.startActivity(myIntent)
    }
}