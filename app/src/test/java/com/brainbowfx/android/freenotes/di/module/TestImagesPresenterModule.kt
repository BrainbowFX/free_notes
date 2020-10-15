package com.brainbowfx.android.freenotes.di.module

import com.brainbowfx.android.freenotes.domain.interactor.CreateImageFile
import com.brainbowfx.android.freenotes.presentation.abstraction.PermissionManager
import dagger.Module
import dagger.Provides
import org.mockito.Mockito

@Module
class TestImagesPresenterModule {

    @Provides
    fun provideCreateImageFileMock(): CreateImageFile = Mockito.mock(CreateImageFile::class.java)

    @Provides
    fun providePermissionManagerMock(): PermissionManager = Mockito.mock(PermissionManager::class.java)

}