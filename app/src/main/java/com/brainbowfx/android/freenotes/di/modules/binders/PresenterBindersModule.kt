package com.brainbowfx.android.freenotes.di.modules.binders

import com.brainbowfx.android.freenotes.data.database.ImagesRepositoryImpl
import com.brainbowfx.android.freenotes.data.database.NotesRepositoryImpl
import com.brainbowfx.android.freenotes.data.images.FileUriProviderImpl
import com.brainbowfx.android.freenotes.data.images.ImageFileManagerImpl
import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import com.brainbowfx.android.freenotes.di.scopes.Presenter
import com.brainbowfx.android.freenotes.domain.abstraction.FileUriProvider
import com.brainbowfx.android.freenotes.domain.abstraction.ImageFileManager
import com.brainbowfx.android.freenotes.domain.repository.ImagesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class PresenterBindersModule {

    @Presenter
    @Binds
    abstract fun bindNotesRepository(notesRepositoryImpl: NotesRepositoryImpl): NotesRepository

    @Presenter
    @Binds
    abstract fun bindImagesRepository(imagesRepositoryImpl: ImagesRepositoryImpl): ImagesRepository

    @Presenter
    @Binds
    abstract fun provideImageUriProvider(fileUriProviderImpl: FileUriProviderImpl): FileUriProvider

    @Presenter
    @Binds
    abstract fun provideImageFileGenerator(imageFileManagerImpl: ImageFileManagerImpl): ImageFileManager
}