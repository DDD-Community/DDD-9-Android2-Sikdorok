package com.ddd.sikdorok.find_password

import androidx.activity.viewModels
import com.ddd.sikdorok.find_password.databinding.ActivityFindPasswordBinding
import com.example.core_ui.base.BaseActivity
import com.example.core_ui.base.BaseViewModel

class FindPasswordActivity : BaseActivity<ActivityFindPasswordBinding>(ActivityFindPasswordBinding::inflate) {

    override val viewModel: BaseViewModel by viewModels()

    override fun initLayout() {}

    override fun setupCollect() {}
}
