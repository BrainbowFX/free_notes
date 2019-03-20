package com.brainbowfx.android.simplenotes.domain.interactor

abstract class UseCase<in I, out T> {

    abstract suspend fun execute(params: I): T

}