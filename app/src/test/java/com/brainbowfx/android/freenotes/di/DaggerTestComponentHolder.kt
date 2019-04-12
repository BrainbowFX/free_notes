package com.brainbowfx.android.freenotes.di

import com.brainbowfx.android.freenotes.di.component.DaggerTestComponent
import com.brainbowfx.android.freenotes.di.component.TestComponent
import com.brainbowfx.android.freenotes.di.module.TestCreateImageFileModule
import com.brainbowfx.android.freenotes.di.module.TestDispatchersModule
import com.brainbowfx.android.freenotes.di.module.TestImagesPresenterModule
import com.brainbowfx.android.freenotes.di.module.TestNotePresenterModule

object DaggerTestComponentHolder {
    val testComponent: TestComponent = DaggerTestComponent.builder()
        .testCreateImageFileModule(TestCreateImageFileModule())
        .testImagesPresenterModule(TestImagesPresenterModule())
        .testDispatchersModule(TestDispatchersModule())
        .testNotePresenterModule(TestNotePresenterModule())
        .build()

}