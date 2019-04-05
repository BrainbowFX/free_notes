package com.brainbowfx.android.freenotes.di.modules

import android.content.Context
import android.os.Environment
import com.brainbowfx.android.freenotes.R
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Singleton

@Module
class FilesModule {

    @Provides
    @Singleton
    fun provideExternalFilesDir(appContext: Context): File = File(
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), appContext.getString(R.string.image_temp_files_directory_name)
    ).also { it.mkdirs() }
}