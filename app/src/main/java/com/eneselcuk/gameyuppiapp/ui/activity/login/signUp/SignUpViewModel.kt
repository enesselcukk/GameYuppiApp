package com.eneselcuk.gameyuppiapp.ui.activity.login.signUp

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.eneselcuk.gameyuppiapp.ui.activity.login.MainActivity
import com.eneselcuk.gameyuppiapp.util.Constants.EMAIL
import com.eneselcuk.gameyuppiapp.util.Constants.ID
import com.eneselcuk.gameyuppiapp.util.Constants.NAME
import com.eneselcuk.gameyuppiapp.util.Constants.SURNAME
import com.eneselcuk.gameyuppiapp.util.Constants.BIRTHDATA
import com.eneselcuk.gameyuppiapp.util.Constants.PASSWORD
import com.eneselcuk.gameyuppiapp.util.Constants.USER
import com.eneselcuk.gameyuppiapp.util.Constants.USERNAME
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class SignUpViewModel(private val context: Activity) : ViewModel() {

    private val _flowGame: MutableStateFlow<FiresUiState> =
        MutableStateFlow(FiresUiState(isLoading = true))
    val flowGame: StateFlow<FiresUiState> get() = _flowGame

    private var fAuth = Firebase.auth
    private val fStore = Firebase.firestore

    fun signUp(
        username: String,
        name: String,
        surName: String,
        birthData: String,
        email: String,
        password: String,
        confirm: String
    ) {

        if (email.isEmpty() && password.isEmpty() && confirm.isEmpty()) {
            Log.d(TAG, "null")
        } else {
            if (password == confirm) {
                fAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            _flowGame.update {
                                it.copy(error = null, isLoading = false, success =  false, navigate = false)
                            }
                            Log.d(TAG, "createUserWithEmail:success")
                            fireStore(username, name, surName, birthData, email, password)
                            click()
                        } else {
                            _flowGame.update {
                                it.copy(error = null, isLoading =  false, success =  false, navigate =  false)
                            }
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            fireStore(null, null)
                        }
                    }
            } else {
                Toast.makeText(context, "password not confirm passford", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun fireStore(
        username: String? = null,
        name: String? = null,
        surName: String? = null,
        birthData: String? = null,
        email: String? = null,
        password: String? = null
    ) {
        fAuth.currentUser?.let { fUser ->
            val data = hashMapOf(
                ID to fUser.uid,
                EMAIL to email,
                PASSWORD to password,
                USERNAME to username,
                NAME to name,
                SURNAME to surName,
                BIRTHDATA to birthData

            )
            fStore.collection(USER).document(fUser.uid)
                .set(data)
                .addOnSuccessListener {
                    Log.d(TAG, "DocumentSnapshot successfully written!")
                    _flowGame.update {
                        it.copy(error = null, isLoading = null, success = true)
                    }
                }.addOnFailureListener { except ->
                    Log.w(TAG, "error", except)
                    _flowGame.update {
                        it.copy(error = except.message, isLoading = null, success = false)
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


