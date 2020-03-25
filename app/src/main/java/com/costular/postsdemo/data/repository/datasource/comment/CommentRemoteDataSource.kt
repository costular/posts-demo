package com.costular.postsdemo.data.repository.datasource.comment

import com.costular.postsdemo.data.model.CommentDTO
import com.costular.postsdemo.data.net.CommentApi
import io.reactivex.Single

interface CommentRemoteDataSource {

    fun getComments(): Single<List<CommentDTO>>

}

class CommentRemoteDataSourceImpl(private val commentApi: CommentApi) : CommentRemoteDataSource {

    override fun getComments(): Single<List<CommentDTO>> = commentApi.getComments()

}