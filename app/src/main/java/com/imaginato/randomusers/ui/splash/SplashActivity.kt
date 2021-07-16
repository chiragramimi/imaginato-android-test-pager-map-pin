package com.imaginato.randomusers.ui.splash

import android.content.Intent
import com.imaginato.randomusers.common.extension.initViewModel
import com.imaginato.randomusers.common.extension.safeObserve
import com.imaginato.randomusers.ui.base.BaseViewModelActivity
import com.imaginato.randomusers.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Splash screen to display app logo
 * This screen will display for 2 seconds
 * When splash screen xml file is not required then pass the null value as layoutRes
 */
@AndroidEntryPoint
class SplashActivity : BaseViewModelActivity<SplashViewModel>(null) {

    override fun buildViewModel() = initViewModel<SplashViewModel>()

    /**
     * Initialize the observers
     */
    override fun initLiveDataObservers() {
        super.initLiveDataObservers()
        with(mViewModel) {
            mSplashStateEvent.safeObserve(this@SplashActivity, {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            })
        }
    }
}
