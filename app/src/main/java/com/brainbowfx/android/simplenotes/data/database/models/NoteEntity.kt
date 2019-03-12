package com.brainbowfx.android.simplenotes.data.database.models

import androidx.room.*
import com.brainbowfx.android.simplenotes.data.mappers.Converters

@Fts4
@Entity(tableName = "notes")
class NoteEntity(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "rowid") var id: Int?,
                 val title: String,
                 val text: String,
                 val date: Long,
                 @TypeConverters(Converters::class) val imagePaths: List<String>)