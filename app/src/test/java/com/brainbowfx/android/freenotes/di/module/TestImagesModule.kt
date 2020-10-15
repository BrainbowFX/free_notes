package com.brainbowfx.android.freenotes.di.module

import com.brainbowfx.android.freenotes.domain.abstraction.FileUriProvider
import com.brainbowfx.android.freenotes.domain.abstraction.ImageFileManager
import com.brainbowfx.android.freenotes.domain.repository.ImagesRepository
import dagger.Module
import dagger.Provides
import org.mockito.Mockito

@Module
class TestImagesModule {

    @Provides
    fun provideImageFileManager(): ImageFileManager = Mockito.mock(ImageFileManager::class.java)

    @Provides
    fun provideFileUriProvider(): FileUriProvider = Mockito.mock(FileUriProvider::class.java)

    @Provides
    fun provideImageRepository(): ImagesRepository = Mockito.mock(ImagesRepository::class.java)
}