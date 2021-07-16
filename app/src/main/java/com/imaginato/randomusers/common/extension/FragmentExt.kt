package com.imaginato.randomusers.common.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.imaginato.randomusers.ui.base.BaseViewModel


inline fun <reified VM : BaseViewModel> Fragment.initActivityViewModel(
    viewModelStoreOwner: ViewModelStoreOwner = requireActivity(),
    factory: ViewModelProvider.Factory = defaultViewModelProviderFactory,
): VM {
    return ViewModelProvider(viewModelStoreOwner, factory)[VM::class.java]
}