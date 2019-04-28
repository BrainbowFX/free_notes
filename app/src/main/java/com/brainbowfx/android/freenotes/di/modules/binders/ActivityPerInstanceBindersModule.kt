package com.brainbowfx.android.freenotes.di.modules.binders

import com.brainbowfx.android.freenotes.di.scopes.ActivityPerInstance
import com.brainbowfx.android.freenotes.domain.router.NotesEditRouter
import com.brainbowfx.android.freenotes.presentation.navigation.NotesEditRouterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ActivityPerInstanceBindersModule {

    @ActivityPerInstance
    @Binds
    abstract fun bindRouter(routerImpl: NotesEditRouterImpl): NotesEditRouter
}