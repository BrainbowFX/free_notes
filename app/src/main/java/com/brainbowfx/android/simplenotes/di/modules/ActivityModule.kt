package com.brainbowfx.android.simplenotes.di.modules

import android.content.Intent
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.brainbowfx.android.simplenotes.di.scopes.Activity
import dagger.Module
import dagger.Provides
import java.lang.ref.SoftReference

import javax.inject.Named

@Module
class ActivityModule(appCompatActivity: AppCompatActivity) {

    private var activity: SoftReference<AppCompatActivity> = SoftReference(appCompatActivity)

    @Provides
    @Activity
    @Named("TakePhotoIntent")
    fun provideTakePhotoIntent(): Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

    @Provides
    @Activity
    fun provideSupportFragmentManager(): FragmentManager? = activity.get()?.supportFragmentManager
}
