package com.ddd.sikdorok.find_password

import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.ddd.sikdorok.extensions.textChanges
import com.ddd.sikdorok.find_password.databinding.ActivityFindPasswordBinding
import com.example.core_ui.base.BackFrameActivity
import com.example.core_ui.base.BaseActivity
import com.example.core_ui.base.BaseViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class FindPasswordActivity : BackFrameActivity<ActivityFindPasswordBinding>(ActivityFindPasswordBinding::inflate) {

    override val viewModel: FindPasswordViewModel by viewModels()
    override val backFrame: FrameLayout by lazy { binding.frameBack }

    override fun initLayout() {
        binding.editEmail.textChanges()
            .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
            .onEach { text ->
                viewModel.event(FindPasswordContract.Event.InputEmail(text.toString()))
            }
            .launchIn(lifecycleScope)

        binding.tvSubmit.setOnClickListener {
            viewModel.event(FindPasswordContract.Event.Submit(binding.editEmail.text.toString()))
        }

        binding.tvSubmit.isEnabled = false
        binding.tvSubmit.isSelected = false
    }

    override fun onClickBackFrameIcon() {
        viewModel.event(FindPasswordContract.Event.OnClickLeftIcon)
    }

    override fun setupCollect() {
        viewModel.effect
            .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
            .onEach { sideEffect ->
                when(sideEffect) {
                    FindPasswordContract.SideEffect.NaviToBack -> {
                        finish()
                    }
                    FindPasswordContract.SideEffect.NaviToSuccess -> {

                    }
                    FindPasswordContract.SideEffect.InValidateEmail -> {
                        binding.inputEmail.error = "올바른 이메일 주소를 입력해주세요"
                        binding.tvSubmit.isEnabled = false
                        binding.tvSubmit.isSelected = false
                    }
                    FindPasswordContract.SideEffect.ValidateEmail -> {
                        binding.inputEmail.error = null
                        binding.tvSubmit.isEnabled = true
                        binding.tvSubmit.isSelected = true
                    }
                    is FindPasswordContract.SideEffect.ShowSnackBar -> {
                        Snackbar.make(binding.root, sideEffect.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
            .launchIn(lifecycleScope)
    }
}
