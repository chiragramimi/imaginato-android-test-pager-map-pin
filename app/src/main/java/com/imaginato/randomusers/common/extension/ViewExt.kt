package com.imaginato.randomusers.common.extension

import android.view.View
import androidx.core.view.isVisible

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.hideView() {
    this.isVisible = false
}

fun View.showView() {
    this.isVisible = true
}