package com.brainbowfx.android.simplenotes.domain.interactor

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

abstract class UseCase<in I, out T>(private val backgroundContext: CoroutineContext = Dispatchers.IO) {

    abstract suspend fun run(params: I): T

    fun execute(params: I): Deferred<T> = GlobalScope.async(backgroundContext) { run(params) }

}