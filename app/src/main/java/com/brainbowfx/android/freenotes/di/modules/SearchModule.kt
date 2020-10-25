package com.brainbowfx.android.freenotes.di.modules

import android.content.Context
import android.graphics.drawable.GradientDrawable
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.di.scopes.Presenter
import com.brainbowfx.android.freenotes.presentation.toDrawable
import com.brainbowfx.android.freenotes.presentation.utils.Selection
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @Provides
    @Presenter
    fun provideSelection(): Selection = Selection()

    @Provides
    @Presenter
    fun provideItemDecorator(context: Context): RecyclerView.ItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL).apply {
        context.toDrawable(R.drawable.sh_divider)?.let { setDrawable(it) }
    }
}