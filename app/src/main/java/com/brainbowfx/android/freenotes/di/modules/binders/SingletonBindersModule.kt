package com.brainbowfx.android.freenotes.di.modules.binders

import com.brainbowfx.android.freenotes.data.CoroutineDispatchersProviderImpl
import com.brainbowfx.android.freenotes.domain.CoroutineDispatchersProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class SingletonBindersModule {

    @Singleton
    @Binds
    abstract fun provideCoroutineDispatcherProvider(coroutineDispatchersProviderImpl: CoroutineDispatchersProviderImpl): CoroutineDispatchersProvider
}