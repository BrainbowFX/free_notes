package com.brainbowfx.android.simplenotes.data.mappers

import android.net.Uri
import com.brainbowfx.android.simplenotes.domain.mappers.Mapper

class UrlToUriMapper : Mapper<String, Uri> {
    override fun map(input: String): Uri = Uri.parse(input)
}