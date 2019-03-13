package com.brainbowfx.android.simplenotes.domain.interactor

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class UseCase<in I, out T>(private val background: CoroutineDispatcher = Dispatchers.Default) {

    abstract suspend fun run(params: I): T

    fun execute(params: I): Deferred<T> = GlobalScope.async(background) { run(params) }

}