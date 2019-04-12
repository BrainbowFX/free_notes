package com.brainbowfx.android.freenotes.di.module

import com.brainbowfx.android.freenotes.domain.CoroutineDispatchersProvider
import dagger.Module
import dagger.Provides
import org.mockito.Mockito

@Module
class TestDispatchersModule {

    @Provides
    fun provideCoroutineDispatchersProviderMock(): CoroutineDispatchersProvider =
        Mockito.mock(CoroutineDispatchersProvider::class.java)
}