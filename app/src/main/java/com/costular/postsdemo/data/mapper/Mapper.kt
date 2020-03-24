package com.costular.postsdemo.data.mapper

interface Mapper<in M, out T> {

    fun map(input: M): T

}