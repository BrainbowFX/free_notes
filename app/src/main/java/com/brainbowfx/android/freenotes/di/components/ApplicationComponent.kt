package com.brainbowfx.android.freenotes.di.components

import com.brainbowfx.android.freenotes.di.modules.*
import com.brainbowfx.android.freenotes.di.modules.binders.SingletonBindersModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    DatabaseModule::class,
    FilesModule::class,
    SingletonBindersModule::class
])
interface ApplicationComponent {
    fun activityPerInstanceSubComponent(): ActivityPerInstanceSubComponent.Builder
}