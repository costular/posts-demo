package com.costular.postsdemo.data.repository.datasource.comment

import com.costular.postsdemo.data.model.CommentDTO
import io.reactivex.Single

interface CommentRemoteDataSource {

    fun getComments(): Single<List<CommentDTO>>

}

class CommentRemoteDataSourceImpl : CommentRemoteDataSource {

    override fun getComments(): Single<List<CommentDTO>> = Single.just(listOf()) // TODO

}