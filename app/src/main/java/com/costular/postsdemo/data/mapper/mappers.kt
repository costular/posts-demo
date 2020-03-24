package com.costular.postsdemo.data.mapper


fun <M, T> Mapper<M, T>.mapList(input: List<M>): List<T> = input.map { item -> map(item) }
