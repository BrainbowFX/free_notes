package com.brainbowfx.android.freenotes.di.modules

import android.content.Context
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val appContext: Context) {

    @Provides
    @Singleton
    fun provideAppContext(): Context = appContext

    @Provides
    @Singleton
    fun provideLayoutInflatter(appContext: Context): LayoutInflater =
        appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
}