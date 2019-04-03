package com.brainbowfx.android.simplenotes.di.modules.binders

import com.brainbowfx.android.simplenotes.di.scopes.ActivityPerInstance
import com.brainbowfx.android.simplenotes.domain.router.Router
import com.brainbowfx.android.simplenotes.presentation.navigation.RouterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ActivityPerInstanceBindersModule {

    @ActivityPerInstance
    @Binds
    abstract fun bindRouter(routerImpl: RouterImpl): Router
}