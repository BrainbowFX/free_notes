package com.brainbowfx.android.freenotes.domain.mappers

interface Mapper<I, O> {
    fun map(input: I): O
}