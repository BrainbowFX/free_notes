package com.brainbowfx.android.freenotes.di.modules

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.di.scopes.Activity
import com.brainbowfx.android.freenotes.domain.abstraction.ImageViewer
import com.brainbowfx.android.freenotes.presentation.abstraction.FloatingActionButtonOwner
import com.brainbowfx.android.freenotes.presentation.abstraction.PermissionManager
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var appCompatActivity: AppCompatActivity) {

    @Provides
    @Activity
    fun provideSupportFragmentManager(): FragmentManager? = appCompatActivity.supportFragmentManager

    @Provides
    @Activity
    fun providePermissionManager(): PermissionManager = appCompatActivity as PermissionManager

    @Provides
    @Activity
    fun provideNavigationController(): NavController? = appCompatActivity.findNavController(R.id.navigationHost)

    @Provides
    @Activity
    fun provideImageViewer(): ImageViewer = appCompatActivity as ImageViewer

    @Provides
    @Activity
    fun provideFloatingActionButtonOwner(): FloatingActionButtonOwner = appCompatActivity as FloatingActionButtonOwner

    @Provides
    @Activity
    fun provideLayoutInflater(): LayoutInflater = appCompatActivity.layoutInflater

}
