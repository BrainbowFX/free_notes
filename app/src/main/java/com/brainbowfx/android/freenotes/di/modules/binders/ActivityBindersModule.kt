package com.brainbowfx.android.freenotes.di.modules.binders

import com.brainbowfx.android.freenotes.di.scopes.Activity
import com.brainbowfx.android.freenotes.domain.router.NotesRouter
import com.brainbowfx.android.freenotes.presentation.navigation.NotesRouterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ActivityBindersModule {

    @Activity
    @Binds
    abstract fun bindRouter(routerImpl: NotesRouterImpl): NotesRouter
}