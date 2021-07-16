package com.imaginato.randomusers.domain.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * base class for use case
 */
abstract class BaseUseCase<out S, in T> where S : Any {

    abstract suspend fun execute(params: T): S

    /**
     * Coroutines used for API calling
     */
    operator fun invoke(
        context: CoroutineContext = Dispatchers.IO,
        scope: CoroutineScope,
        params: T,
        onResult: (Result<Throwable, S>) -> Unit = {}
    ) {
        val job = scope.async(context) { getResult { execute(params) } }
        scope.launch(Dispatchers.Main) { onResult(job.await()) }
    }

}
