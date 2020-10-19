package com.brainbowfx.android.freenotes.presentation.presenter

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.brainbowfx.android.freenotes.domain.CoroutineDispatchersProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

open class ScopedPresenter<View : MvpView> : MvpPresenter<View>(), CoroutineScope {

    @Inject
    lateinit var coroutineDispatchersProvider: CoroutineDispatchersProvider

    override val coroutineContext: CoroutineContext by lazy {
        coroutineDispatchersProvider.getMainDispatcher() + SupervisorJob()
    }
}