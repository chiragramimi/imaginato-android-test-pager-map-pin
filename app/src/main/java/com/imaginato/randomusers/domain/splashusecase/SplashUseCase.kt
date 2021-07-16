package com.imaginato.randomusers.domain.splashusecase

import com.imaginato.randomusers.domain.base.BaseUseCase
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * Splash use case
 */
class SplashUseCase @Inject constructor() : BaseUseCase<Boolean, SplashUseCase.Params>() {

    data class Params(val delayInMillis: Long)

    /**
     * delay as per params value
     */
    override suspend fun execute(params: Params): Boolean {
        delay(params.delayInMillis)
        return true
    }
}
