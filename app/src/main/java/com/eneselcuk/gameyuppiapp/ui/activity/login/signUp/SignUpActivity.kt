package com.eneselcuk.gameyuppiapp.ui.activity.login.signUp



import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.eneselcuk.gameyuppiapp.base.BaseActivity
import com.eneselcuk.gameyuppiapp.databinding.ActivitySignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>(ActivitySignUpBinding::inflate) {
    private val viewModel: SignUpViewModel by lazy {
        SignUpViewModel(this@SignUpActivity)
    }
    override fun definition() {
        activityDataBinding.setData = this
        activityDataBinding.clickSignUp = viewModel
    }
    override fun setObserver() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.flowGame
                        .collect {
                            uiState(it)
                        }
                }
            }
        }
    }
    private fun uiState(fireUi: FiresUiState) {
        fireUi.error.let {
            "DocumentSnapshot successfully written!"
        }
    }
}