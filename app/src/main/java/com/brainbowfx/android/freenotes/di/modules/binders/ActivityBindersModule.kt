package com.brainbowfx.android.freenotes.di.modules.binders

import com.brainbowfx.android.freenotes.di.scopes.Activity
import com.brainbowfx.android.freenotes.domain.router.NotesRouter
import com.brainbowfx.android.freenotes.domain.router.Router
import com.brainbowfx.android.freenotes.presentation.navigation.NotesRouterImpl
import com.brainbowfx.android.freenotes.presentation.navigation.SearchRouterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ActivityBindersModule {

    @Activity
    @Binds
    abstract fun bindNotesRouter(routerImpl: NotesRouterImpl): NotesRouter

    @Activity
    @Binds
    abstract fun bindSearchRouter(routerImpl: SearchRouterImpl): Router
}