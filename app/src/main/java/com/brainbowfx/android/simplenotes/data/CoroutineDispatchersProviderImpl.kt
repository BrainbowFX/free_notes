package com.brainbowfx.android.simplenotes.data

import com.brainbowfx.android.simplenotes.domain.CoroutineDispatchersProvider
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CoroutineDispatchersProviderImpl @Inject constructor() : CoroutineDispatchersProvider {

    override fun getMainDispatcher() = Dispatchers.Main

    override fun getIODispatcher() = Dispatchers.IO

    override fun getDefaultDispatcher() = Dispatchers.Default
}