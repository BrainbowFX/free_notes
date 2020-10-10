package com.brainbowfx.android.freenotes.data.database

import com.brainbowfx.android.freenotes.data.database.dao.ImagesDao
import com.brainbowfx.android.freenotes.di.scopes.Presenter
import com.brainbowfx.android.freenotes.domain.repository.ImagesRepository
import javax.inject.Inject

@Presenter
class ImagesRepositoryImpl @Inject constructor(private val imagesDao: ImagesDao) :
    ImagesRepository {
    override suspend fun deleteByIds(imageIds: LongArray): Int = imagesDao.deleteByIds(imageIds)
}