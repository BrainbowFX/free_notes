package com.brainbowfx.android.freenotes.domain.repository

interface ImagesRepository {
    suspend fun deleteByIds(imageIds: LongArray): Int
}