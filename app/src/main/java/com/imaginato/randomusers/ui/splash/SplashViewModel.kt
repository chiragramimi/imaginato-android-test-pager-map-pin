package com.imaginato.randomusers.ui.splash

import androidx.lifecycle.viewModelScope
import com.imaginato.randomusers.domain.splashusecase.SplashUseCase
import com.imaginato.randomusers.ui.base.BaseViewModel
import com.imaginato.randomusers.ui.base.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Splash view model
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val splashUseCase: SplashUseCase
) : BaseViewModel() {

    internal val mSplashStateEvent = SingleLiveEvent<Any>()

    override fun loadPage(multipleTimes: Boolean): Boolean {
        navigateNextScreen()
        return super.loadPage(multipleTimes)
    }

    /**
     * calling splash use case object for delay for 2 seconds
     */
    private fun navigateNextScreen() {
        val params = SplashUseCase.Params(TimeUnit.SECONDS.toMillis(2))
        splashUseCase.invoke(scope = viewModelScope, params = params) {
            it.result(mSplashStateEvent::setValue) { throwable ->
                Timber.e(throwable)
            }
        }
    }
}
