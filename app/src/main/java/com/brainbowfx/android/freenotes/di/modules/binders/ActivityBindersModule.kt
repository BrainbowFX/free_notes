package com.brainbowfx.android.freenotes.di.modules.binders

import com.brainbowfx.android.freenotes.data.database.NotesRepositoryImpl
import com.brainbowfx.android.freenotes.data.images.FileUriProviderImpl
import com.brainbowfx.android.freenotes.data.images.ImageFileGeneratorImpl
import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import com.brainbowfx.android.freenotes.di.scopes.Activity
import com.brainbowfx.android.freenotes.domain.abstraction.FileUriProvider
import com.brainbowfx.android.freenotes.domain.abstraction.ImageFileGenerator
import dagger.Binds
import dagger.Module

@Module
abstract class ActivityBindersModule {

    @Activity
    @Binds
    abstract fun bindNotesRepository(notesRepositoryImpl: NotesRepositoryImpl): NotesRepository

    @Activity
    @Binds
    abstract fun provideImageUriProvider(fileUriProviderImpl: FileUriProviderImpl): FileUriProvider

    @Activity
    @Binds
    abstract fun provideImageFileGenerator(imageFileGeneratorImpl: ImageFileGeneratorImpl): ImageFileGenerator
}