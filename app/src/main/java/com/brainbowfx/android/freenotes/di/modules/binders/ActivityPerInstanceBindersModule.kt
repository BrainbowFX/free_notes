package com.brainbowfx.android.freenotes.di.modules.binders

import com.brainbowfx.android.freenotes.di.scopes.ActivityPerInstance
import com.brainbowfx.android.freenotes.domain.router.Router
import com.brainbowfx.android.freenotes.presentation.navigation.RouterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ActivityPerInstanceBindersModule {

    @ActivityPerInstance
    @Binds
    abstract fun bindRouter(routerImpl: RouterImpl): Router
}