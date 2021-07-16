package com.imaginato.randomusers.ui.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes

/**
 * If activity wants viewModel then extend BaseViewModelActivity class
 */
abstract class BaseViewModelActivity<VM : BaseViewModel>(@LayoutRes layoutRes: Int?) :
    BaseActivity(layoutRes) {

    val mViewModel by lazy { buildViewModel() }

    protected abstract fun buildViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLiveDataObservers()
        mViewModel.loadPage()
    }

    @CallSuper
    protected open fun initLiveDataObservers() = Unit
}
