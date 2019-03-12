package com.brainbowfx.android.simplenotes.di.modules

import android.content.Context
import androidx.room.Room
import com.brainbowfx.android.simplenotes.data.database.ApplicationDatabase
import com.brainbowfx.android.simplenotes.data.database.dao.NotesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideApplicationDatabase(appContext: Context): ApplicationDatabase =
        Room.databaseBuilder(appContext, ApplicationDatabase::class.java, "easy_note").build()

    @Provides
    @Singleton
    fun provideNotesDao(applicationDatabase: ApplicationDatabase): NotesDao = applicationDatabase.getNotesDao()
}