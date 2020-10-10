package com.brainbowfx.android.freenotes.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.brainbowfx.android.freenotes.data.database.models.ImageEntity

@Dao
interface ImagesDao : BaseDao<ImageEntity> {

    @Query("DELETE FROM images WHERE `rowid` IN (:imageIds) ")
    suspend fun deleteByIds(imageIds: LongArray): Int

    @Query("SELECT * FROM images")
    suspend fun getAll(): List<ImageEntity>
}