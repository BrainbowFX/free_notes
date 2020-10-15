package com.brainbowfx.android.freenotes.di.component

import com.brainbowfx.android.freenotes.CreateImageFileTest
import com.brainbowfx.android.freenotes.DeleteImagesTest
import com.brainbowfx.android.freenotes.SaveNoteTest
import com.brainbowfx.android.freenotes.di.module.*
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
        TestImagesModule::class,
        TestNotesModule::class
    ]
)
interface TestComponent {
    fun inject(imagesPresenter: ImagesPresenter)
    fun inject(notePresenter: NotePresenter)
    fun inject(createImageFileTest: CreateImageFileTest)
    fun inject(createImageFile: CreateImageFile)
    fun inject(deleteImagesTest: DeleteImagesTest)
    fun inject(saveNoteTest: SaveNoteTest)
}