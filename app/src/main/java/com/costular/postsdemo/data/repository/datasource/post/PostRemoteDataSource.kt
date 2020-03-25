package com.costular.postsdemo.data.repository.datasource.post

import com.costular.postsdemo.data.model.CommentDTO
import com.costular.postsdemo.data.model.PostDTO
import com.costular.postsdemo.data.model.UserDTO
import io.reactivex.Single

interface PostRemoteDataSource {

    fun getPosts(): Single<List<PostDTO>>

}

class PostRemoteDataSourceImpl : PostRemoteDataSource {

    override fun getPosts(): Single<List<PostDTO>> = Single.just(listOf())

}