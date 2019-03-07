package com.brainbowfx.android.simplenotes.di.modules

import com.brainbowfx.android.simplenotes.di.scopes.Activity
import dagger.Module
import dagger.Provides
import java.text.SimpleDateFormat
import java.util.*

@Module
class DateModule(private val dateFormat: String) {

    @Provides
    @Activity
    fun provideDefaultLocale(): Locale = Locale.getDefault()

    @Activity
    @Provides
    fun provideSimpeDateFormat(locale: Locale): SimpleDateFormat = SimpleDateFormat(dateFormat, locale)
}