package com.brainbowfx.android.freenotes.di.modules

import android.os.Environment
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Singleton

@Module
class FilesModule {

    @Provides
    @Singleton
    fun provideExternalFilesDir(): File = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "SimpleNotes").also { it.mkdirs() }
}