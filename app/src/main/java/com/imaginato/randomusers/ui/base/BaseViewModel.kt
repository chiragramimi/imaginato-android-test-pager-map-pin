package com.imaginato.randomusers.ui.base

import androidx.lifecycle.ViewModel
import androidx.annotation.CallSuper
import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.databinding.PropertyChangeRegistry

abstract class BaseViewModel : ViewModel(), Observable {
    private var isFirst = true

    private val mCallbacks = PropertyChangeRegistry()


    /**
     * Only can call once per lifecycle
     * @param multipleTimes (OPTIONAL) set it to true to make multiple call capability
     */
    @CallSuper
    open fun loadPage(multipleTimes: Boolean = false): Boolean {
        if (isFirst) {
            isFirst = false
            return true
        }

        return isFirst || multipleTimes
    }

    override fun addOnPropertyChangedCallback(
        callback: OnPropertyChangedCallback?
    ) {
        mCallbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(
        callback: OnPropertyChangedCallback?
    ) {
        mCallbacks.remove(callback)
    }

    /**
     * Notifies observers that all properties of this instance have changed.
     */
    open fun notifyChange() {
        mCallbacks.notifyCallbacks(this, 0, null)
    }

    /**
     * Notifies observers that a specific property has changed. The getter for the
     * property that changes should be marked with the @Bindable annotation to
     * generate a field in the BR class to be used as the fieldId parameter.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    open fun notifyPropertyChanged(fieldId: Int) {
        mCallbacks.notifyCallbacks(this, fieldId, null)
    }
}
