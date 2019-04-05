package com.brainbowfx.android.freenotes.di.modules

import com.brainbowfx.android.freenotes.di.scopes.Activity
import dagger.Module
import dagger.Provides
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Named

@Module
class DateModule {

    @Provides
    @Activity
    fun provideDefaultLocale(): Locale = Locale.getDefault()

    @Activity
    @Provides
    @Named("TimeStamp")
    fun provideTimeStampSimpeDateFormat(locale: Locale): SimpleDateFormat = SimpleDateFormat(TIMESTAMP, locale)

    @Activity
    @Provides
    @Named("DateTime")
    fun provideDateTimeSimpeDateFormat(locale: Locale): SimpleDateFormat = SimpleDateFormat(DATETIME, locale)
}