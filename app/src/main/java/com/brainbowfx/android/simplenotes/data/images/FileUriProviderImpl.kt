package com.brainbowfx.android.simplenotes.data.images

import android.content.Context
import androidx.core.content.FileProvider
import com.brainbowfx.android.simplenotes.di.scopes.Activity
import com.brainbowfx.android.simplenotes.domain.abstraction.FileUriProvider
import java.io.File
import javax.inject.Inject

@Activity
class FileUriProviderImpl @Inject constructor(val context: Context) :
    FileUriProvider {

    override fun getUriForFile(file: File): String =
        FileProvider.getUriForFile(context, "com.brainbowfx.android.simplenotes.fileprovider", file).toString()

}