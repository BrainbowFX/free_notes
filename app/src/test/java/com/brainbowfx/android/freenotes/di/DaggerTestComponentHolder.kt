package com.brainbowfx.android.freenotes.di

import com.brainbowfx.android.freenotes.di.component.DaggerTestComponent
import com.brainbowfx.android.freenotes.di.component.TestComponent
import com.brainbowfx.android.freenotes.di.module.*

object DaggerTestComponentHolder {
    val testComponent: TestComponent = DaggerTestComponent.builder()
        .testImagesModule(TestImagesModule())
        .testImagesPresenterModule(TestImagesPresenterModule())
        .testDispatchersModule(TestDispatchersModule())
        .testNotePresenterModule(TestNotePresenterModule())
        .testNotesModule(TestNotesModule())
        .build()

}