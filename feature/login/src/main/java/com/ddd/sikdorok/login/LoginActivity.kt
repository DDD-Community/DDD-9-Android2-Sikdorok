package com.ddd.sikdorok.login

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.ddd.sikdorok.login.databinding.ActivityLoginBinding
import com.ddd.sikdorok.navigator.signin.SignInNavigator
import com.example.core_ui.base.BaseActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    @Inject
    lateinit var signInNavigator: SignInNavigator

    override val viewModel by viewModels<LoginViewModel>()
    override fun initLayout() {}

    private val callback: (token: OAuthToken?, error: Throwable?) -> Unit = { token, error ->
        if(error != null) {
            Log.e(TAG, "카카오 로그인 실패")
        } else if(token != null) {
            viewModel.event(LoginContract.Event.CheckKakaoUser(token.accessToken))
        }
    }

    override fun setupCollect() {
        viewModel.effect
            .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
            .onEach { effect ->
                when(effect) {
                    is LoginContract.SideEffect.NaviToSikdorokLogin -> {
                        startActivity(signInNavigator.start(this))
                    }
                    is LoginContract.SideEffect.NaviToSignUp -> {
                        Log.e("result", effect.email.orEmpty())
                    }
                    is LoginContract.SideEffect.NaviToKakaoLogin -> {
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                    }
                    is LoginContract.SideEffect.NaviToHome -> {
                        finish()
                    }
                }
            }
            .launchIn(lifecycleScope)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.viewEmail.setOnClickListener {
            viewModel.event(LoginContract.Event.RequestSikdorokLogin)
        }

        binding.viewKakao.setOnClickListener {
            viewModel.event(LoginContract.Event.RequestKakaoLogin)
        }
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }

    companion object {
        private const val TAG = "KAKAO"
    }
}
