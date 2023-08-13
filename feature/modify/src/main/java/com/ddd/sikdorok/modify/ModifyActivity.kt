package com.ddd.sikdorok.modify

import android.widget.FrameLayout
import androidx.activity.viewModels
import com.ddd.sikdorok.core_ui.base.BackFrameActivity
import com.ddd.sikdorok.core_ui.base.BaseViewModel
import com.ddd.sikdorok.modify.databinding.ActivityModifyBinding

class ModifyActivity : BackFrameActivity<ActivityModifyBinding>(ActivityModifyBinding::inflate) {

    override val backFrame: FrameLayout by lazy { binding.frameBack }

    override val viewModel: BaseViewModel by viewModels()

    override fun initLayout() {

    }

    override fun onClickBackFrameIcon() {

    }

    override fun setupCollect() {
    }
}