package com.brainbowfx.android.freenotes.data.images

import android.content.Context
import androidx.core.content.FileProvider
import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.di.scopes.Activity
import com.brainbowfx.android.freenotes.domain.abstraction.FileUriProvider
import java.io.File
import javax.inject.Inject

@Activity
class FileUriProviderImpl @Inject constructor(val context: Context) :
    FileUriProvider {

    override fun getUriForFile(file: File): String =
        FileProvider.getUriForFile(context, context.getString(R.string.file_provider_authority), file).toString()

}