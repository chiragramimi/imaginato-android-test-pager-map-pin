package com.imaginato.randomusers.ui.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * BaseActivity for set layout resource file and initialize view
 */
abstract class BaseActivity(@LayoutRes var layoutRes: Int?) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutRes?.let { setContentView(it) }
        initViews()
    }

    @CallSuper
    protected open fun initViews() {
    }
}
