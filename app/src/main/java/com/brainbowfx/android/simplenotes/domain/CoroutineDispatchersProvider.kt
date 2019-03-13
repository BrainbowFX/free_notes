package com.brainbowfx.android.simplenotes.domain

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchersProvider {
    fun getMainDispatcher(): CoroutineDispatcher

    fun getIODispatcher(): CoroutineDispatcher

    fun getDefaultDispatcher(): CoroutineDispatcher
}