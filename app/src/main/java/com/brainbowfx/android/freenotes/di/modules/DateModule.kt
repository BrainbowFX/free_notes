package com.brainbowfx.android.freenotes.di.modules

import com.brainbowfx.android.freenotes.DATETIME
import com.brainbowfx.android.freenotes.DATETIME_NAMED_ID
import com.brainbowfx.android.freenotes.TIMESTAMP
import com.brainbowfx.android.freenotes.TIMESTAMP_NAMED_ID
import com.brainbowfx.android.freenotes.di.scopes.Presenter
import dagger.Module
import dagger.Provides
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Named

@Module
class DateModule {

    @Provides
    @Presenter
    fun provideDefaultLocale(): Locale = Locale.getDefault()

    @Presenter
    @Provides
    @Named(TIMESTAMP_NAMED_ID)
    fun provideTimeStampSimpeDateFormat(locale: Locale): SimpleDateFormat = SimpleDateFormat(TIMESTAMP, locale)

    @Presenter
    @Provides
    @Named(DATETIME_NAMED_ID)
    fun provideDateTimeSimpeDateFormat(locale: Locale): SimpleDateFormat = SimpleDateFormat(DATETIME, locale)
}