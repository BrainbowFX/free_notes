package com.brainbowfx.android.freenotes.di.components

import com.brainbowfx.android.freenotes.di.modules.*
import com.brainbowfx.android.freenotes.di.modules.binders.SingletoneBindersModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    DatabaseModule::class,
    FilesModule::class,
    SingletoneBindersModule::class
])
interface ApplicationComponent {
    fun activityPerInstanceSubComponent(): ActivityPerInstanceSubcomponent.Builder
}