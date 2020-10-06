package com.brainbowfx.android.freenotes.data.database.dao

import androidx.room.*
import com.brainbowfx.android.freenotes.data.database.models.BaseEntity

interface BaseDao<T : BaseEntity> {

    @Insert
    suspend fun insert(entity: T): Long

    @Insert
    suspend fun insert(entities: List<T>)

    @Delete
    suspend fun delete(entity: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entities: List<T>)
}