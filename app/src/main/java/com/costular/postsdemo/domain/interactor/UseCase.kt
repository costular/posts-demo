package com.costular.postsdemo.domain.interactor

abstract class UseCase<out T, in Params> {

    abstract fun build(params: Params): T

    fun execute(params: Params): T =
        build(params)

    class None

}

fun <T> UseCase<T, UseCase.None>.execute(): T = execute(UseCase.None())