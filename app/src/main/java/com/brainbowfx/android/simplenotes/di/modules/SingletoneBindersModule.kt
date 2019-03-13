package com.brainbowfx.android.simplenotes.di.modules

import com.brainbowfx.android.simplenotes.data.CoroutineDispatchersProviderImpl
import com.brainbowfx.android.simplenotes.domain.CoroutineDispatchersProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class SingletoneBindersModule {

    @Singleton
    @Binds
    abstract fun provideCoroutineDispatcherProvider(coroutineDispatchersProviderImpl: CoroutineDispatchersProviderImpl): CoroutineDispatchersProvider
}