package com.brainbowfx.android.freenotes.di.module

import com.brainbowfx.android.freenotes.domain.abstraction.FileUriProvider
import com.brainbowfx.android.freenotes.domain.abstraction.ImageFileGenerator
import dagger.Module
import dagger.Provides
import org.mockito.Mockito

@Module
class TestCreateImageFileModule {

    @Provides
    fun provideImageFileGenerator(): ImageFileGenerator = Mockito.mock(ImageFileGenerator::class.java)

    @Provides
    fun provideFileUriProvider(): FileUriProvider = Mockito.mock(FileUriProvider::class.java)
}