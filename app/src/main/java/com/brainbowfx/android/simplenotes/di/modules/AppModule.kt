package com.brainbowfx.android.simplenotes.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val appContext: Context) {

    @Provides
    fun provideAppContext(): Context = appContext
}