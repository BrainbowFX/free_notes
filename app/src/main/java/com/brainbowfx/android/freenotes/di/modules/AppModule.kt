package com.brainbowfx.android.freenotes.di.modules

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import com.brainbowfx.android.freenotes.PREFERENCES
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
    fun getSharedPreferences(): SharedPreferences = appContext.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

}