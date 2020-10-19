package com.brainbowfx.android.freenotes.di.modules

import android.content.Context
import androidx.room.Room
import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.data.database.ApplicationDatabase
import com.brainbowfx.android.freenotes.data.database.dao.ImagesDao
import com.brainbowfx.android.freenotes.data.database.dao.NotesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideApplicationDatabase(appContext: Context): ApplicationDatabase =
        Room.databaseBuilder(
            appContext,
            ApplicationDatabase::class.java,
            appContext.getString(R.string.database_name)
        ).build()

    @Provides
    @Singleton
    fun provideNotesDao(applicationDatabase: ApplicationDatabase): NotesDao = applicationDatabase.getNotesDao()

    @Provides
    fun provideImagesDao(applicationDatabase: ApplicationDatabase): ImagesDao = applicationDatabase.getImagesDao()
}