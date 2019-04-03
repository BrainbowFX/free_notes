package com.brainbowfx.android.simplenotes.di.modules.binders

import com.brainbowfx.android.simplenotes.data.database.NotesRepositoryImpl
import com.brainbowfx.android.simplenotes.data.images.FileUriProviderImpl
import com.brainbowfx.android.simplenotes.data.images.ImageFileGeneratorImpl
import com.brainbowfx.android.simplenotes.domain.repository.NotesRepository
import com.brainbowfx.android.simplenotes.di.scopes.Activity
import com.brainbowfx.android.simplenotes.domain.abstraction.FileUriProvider
import com.brainbowfx.android.simplenotes.domain.abstraction.ImageFileGenerator
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