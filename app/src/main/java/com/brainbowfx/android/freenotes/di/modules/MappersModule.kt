package com.brainbowfx.android.freenotes.di.modules

import android.net.Uri
import com.brainbowfx.android.freenotes.DATETIME_NAMED_ID
import com.brainbowfx.android.freenotes.data.database.models.ImageEntity
import com.brainbowfx.android.freenotes.data.database.models.NoteEntity
import com.brainbowfx.android.freenotes.data.database.models.projections.NoteWithImages
import com.brainbowfx.android.freenotes.data.mappers.*
import com.brainbowfx.android.freenotes.di.scopes.Presenter
import com.brainbowfx.android.freenotes.domain.entities.Image
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import dagger.Module
import dagger.Provides
import java.text.SimpleDateFormat
import javax.inject.Named

@Module
class MappersModule {

    @Provides
    @Presenter
    fun provideNotesToNotesEntityMapper(@Named(DATETIME_NAMED_ID) simpleDateFormat: SimpleDateFormat): Mapper<Note, NoteEntity> =
        NotesToNotesEntityMapper(simpleDateFormat)

    @Provides
    @Presenter
    fun provideImageToImageEntityMapper(): Mapper<Image, ImageEntity> = ImageToImageEntityMapper()

    @Provides
    @Presenter
    fun provideImageEntityToImageMapper(): Mapper<ImageEntity, Image> = ImageEntityToImageMapper()

    @Provides
    @Presenter
    fun provideNotesEntityToNotesMapper(@Named(DATETIME_NAMED_ID) simpleDateFormat: SimpleDateFormat, mapper: Mapper<ImageEntity, Image>): Mapper<NoteWithImages, Note> =
        NotesWithImagesToNotesMapper(simpleDateFormat, mapper)

    @Provides
    @Presenter
    fun provideUrlToUriMapper(): Mapper<String, Uri> = UrlToUriMapper()

}