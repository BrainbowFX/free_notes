package com.brainbowfx.android.freenotes.presentation.presenter

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.brainbowfx.android.freenotes.domain.CoroutineDispatchersProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class ScopedPresenter<View : MvpView> : MvpPresenter<View>(), CoroutineScope {

    abstract var coroutineDispatchersProvider: CoroutineDispatchersProvider

    override val coroutineContext: CoroutineContext by lazy {
        coroutineDispatchersProvider.getMainDispatcher() + SupervisorJob()
    }
}