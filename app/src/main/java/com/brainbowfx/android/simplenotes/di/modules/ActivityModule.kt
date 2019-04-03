package com.brainbowfx.android.simplenotes.di.modules

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.brainbowfx.android.simplenotes.R
import com.brainbowfx.android.simplenotes.di.scopes.ActivityPerInstance
import com.brainbowfx.android.simplenotes.domain.abstraction.CameraController
import com.brainbowfx.android.simplenotes.domain.abstraction.ImageViewer
import com.brainbowfx.android.simplenotes.presentation.utils.PermissionManager
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var appCompatActivity: AppCompatActivity) {

    @Provides
    @ActivityPerInstance
    fun provideSupportFragmentManager(): FragmentManager? = appCompatActivity.supportFragmentManager

    @Provides
    @ActivityPerInstance
    fun providePermissionManager(): PermissionManager = appCompatActivity as PermissionManager

    @Provides
    @ActivityPerInstance
    fun provideNavigationController(): NavController? = appCompatActivity.findNavController(R.id.navigationHost)

    @Provides
    @ActivityPerInstance
    fun provideCameraController(): CameraController = appCompatActivity as CameraController

    @Provides
    @ActivityPerInstance
    fun provideImageViewer(): ImageViewer = appCompatActivity as ImageViewer
}
