package com.brainbowfx.android.simplenotes.di.components

import com.brainbowfx.android.simplenotes.di.modules.*
import com.brainbowfx.android.simplenotes.di.modules.binders.SingletoneBindersModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class, FilesModule::class, SingletoneBindersModule::class])
interface ApplicationComponent {
    fun activitySubcomponent(): ActivitySubcomponent.Builder
}