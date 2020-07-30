package com.example.rocketpunch_interview.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR

abstract class BaseActivity<baseViewBinding: ViewDataBinding, baseViewModel: BaseViewModel> : AppCompatActivity() {
    abstract val layoutResourceId: Int
    private lateinit var viewDataBinding: baseViewBinding
    abstract val viewModel: baseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)
        viewDataBinding.setVariable(BR.viewModel, viewModel)
        viewDataBinding.lifecycleOwner = this
    }
}
