package com.brainbowfx.android.freenotes.di.component

import com.brainbowfx.android.freenotes.CreateImageFileTest
import com.brainbowfx.android.freenotes.di.module.TestCreateImageFileModule
import com.brainbowfx.android.freenotes.di.module.TestDispatchersModule
import com.brainbowfx.android.freenotes.di.module.TestImagesPresenterModule
import com.brainbowfx.android.freenotes.di.module.TestNotePresenterModule
import com.brainbowfx.android.freenotes.domain.interactor.CreateImageFile
import com.brainbowfx.android.freenotes.presentation.presenter.ImagesPresenter
import com.brainbowfx.android.freenotes.presentation.presenter.NotePresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        TestImagesPresenterModule::class,
        TestDispatchersModule::class,
        TestNotePresenterModule::class,
        TestCreateImageFileModule::class
    ]
)
interface TestComponent {
    fun inject(imagesPresenter: ImagesPresenter)
    fun inject(notePresenter: NotePresenter)
    fun inject(createImageFileTest: CreateImageFileTest)
    fun inject(createImageFile: CreateImageFile)
}