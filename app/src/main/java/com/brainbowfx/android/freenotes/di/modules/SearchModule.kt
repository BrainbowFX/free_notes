package com.brainbowfx.android.freenotes.di.modules

import com.brainbowfx.android.freenotes.di.scopes.Presenter
import com.brainbowfx.android.freenotes.presentation.utils.Selection
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @Provides
    @Presenter
    fun provideSelection(): Selection = Selection()
}