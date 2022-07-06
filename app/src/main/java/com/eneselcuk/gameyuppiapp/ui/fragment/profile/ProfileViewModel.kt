package com.eneselcuk.gameyuppiapp.ui.fragment.profile

import android.app.Activity
import android.content.Intent
import android.provider.Telephony.Carriers.USER
import android.util.Log
import androidx.lifecycle.ViewModel
import com.eneselcuk.gameyuppiapp.ui.activity.login.login.LoginActivity
import com.eneselcuk.gameyuppiapp.util.Constants.TAG
import com.eneselcuk.gameyuppiapp.util.Constants.EMAIL
import com.eneselcuk.gameyuppiapp.util.Constants.PASSWORD
import com.eneselcuk.gameyuppiapp.util.Constants.NAME
import com.eneselcuk.gameyuppiapp.util.Constants.SURNAME
import com.eneselcuk.gameyuppiapp.util.Constants.BIRTHDATA
import com.eneselcuk.gameyuppiapp.util.Constants.USERNAME
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update



class ProfileViewModel(
    private val activity: Activity
) : ViewModel() {

    private val _userFlow: MutableStateFlow<ProfileUiState> = MutableStateFlow(ProfileUiState(null))
    val userFlow: StateFlow<ProfileUiState> = _userFlow

    private var user = Firebase.auth
    private val db = Firebase.firestore
    fun getUser() {
        user.uid?.let {
            val docRef = db.collection(USER).document(it)
            docRef.get()
                .addOnSuccessListener { document ->
                    document.let {
                        _userFlow.update { uiState ->
                            val email = document.get(EMAIL)
                            val password = document.get(PASSWORD)
                            val name = document.get(NAME)
                            val username = document.get(USERNAME)
                            val surName = document.get(SURNAME)
                            val birthData = document.get(BIRTHDATA)
                            uiState.copy(
                                username = username,
                                name = name,
                                surname = surName,
                                birthData = birthData,
                                email = email,
                                password = password,
                                Loading = null
                            )
                        }
                    }
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                }
                .addOnFailureListener { Ex ->
                    Log.d(TAG, "DocumentSnapshot data: ${Ex.message}")
                }
        }
    }

    fun userUpdate(
        username: String,
        name: String,
        surname: String,
        birthData: String,
        email: String
    ) {
        user.uid?.let {
            val updRef = db.collection(USER).document(it)
            updRef
                .update(
                    mapOf(
                        USERNAME to username,
                        NAME to name,
                        SURNAME to surname,
                        BIRTHDATA to birthData,
                        EMAIL to email
                    )
                )
                .addOnSuccessListener {
                    user.currentUser?.updateEmail(email)
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d(TAG, "User email address updated.")
                            }
                        }

                    Log.d(TAG, "DocumentSnapshot successfully updated!")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error updating document", e)
                }
        }
    }

    fun signOutClick() {
        with(activity) {
            user.signOut()
            val intent = Intent(activity, LoginActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}

