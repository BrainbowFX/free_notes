package com.brainbowfx.android.simplenotes.di.modules

import android.content.Intent
import android.provider.MediaStore
import com.brainbowfx.android.simplenotes.di.scopes.Activity
import dagger.Module
import dagger.Provides

import javax.inject.Named

@Module
class ActivityModule {

    @Provides
    @Activity
    @Named("TakePhotoIntent")
    fun provideTakePhotoIntent(): Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
}
