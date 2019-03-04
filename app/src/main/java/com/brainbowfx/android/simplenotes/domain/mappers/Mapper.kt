package com.brainbowfx.android.simplenotes.domain.mappers

interface Mapper<I,O> {
    fun map(input: I): O
}