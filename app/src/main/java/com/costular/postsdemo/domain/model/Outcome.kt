package com.costular.postsdemo.domain.model

sealed class Outcome<out T> {

    object Loading : Outcome<Nothing>()

    data class Success<T>(val posts: T): Outcome<T>()

    data class Failure(val error: Throwable): Outcome<Nothing>()

}