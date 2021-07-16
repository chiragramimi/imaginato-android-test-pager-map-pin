package com.imaginato.randomusers.common.extension

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.imaginato.randomusers.ui.base.BaseViewModel

inline fun <reified VM : BaseViewModel> FragmentActivity.initViewModel(
    factory: ViewModelProvider.Factory = defaultViewModelProviderFactory,
): VM {
    return ViewModelProvider(this, factory)[VM::class.java]
}