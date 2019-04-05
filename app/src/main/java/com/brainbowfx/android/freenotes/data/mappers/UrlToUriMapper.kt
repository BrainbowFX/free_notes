package com.brainbowfx.android.freenotes.data.mappers

import android.net.Uri
import com.brainbowfx.android.freenotes.domain.mappers.Mapper

class UrlToUriMapper : Mapper<String, Uri> {
    override fun map(input: String): Uri = Uri.parse(input)
}