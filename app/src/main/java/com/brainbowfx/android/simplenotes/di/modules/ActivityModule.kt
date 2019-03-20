package com.brainbowfx.android.simplenotes.di.modules

import android.content.Intent
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.brainbowfx.android.simplenotes.di.scopes.ActivityPerInstance
import dagger.Module
import dagger.Provides

import javax.inject.Named

@Module
class ActivityModule(private var appCompatActivity: AppCompatActivity) {

    @Provides
    @ActivityPerInstance
    @Named("TakePhotoIntent")
    fun provideTakePhotoIntent(): Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

    @Provides
    @ActivityPerInstance
    fun provideSupportFragmentManager(): FragmentManager? = appCompatActivity.supportFragmentManager

}
