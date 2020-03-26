package com.costular.postsdemo.data.repository.datasource.post

import com.costular.postsdemo.data.model.PostDTO
import com.costular.postsdemo.data.net.PostApi
import io.reactivex.Single

interface PostRemoteDataSource {

    fun getPosts(): Single<List<PostDTO>>

}

class PostRemoteDataSourceImpl(private val postApi: PostApi) : PostRemoteDataSource {

    override fun getPosts(): Single<List<PostDTO>> = postApi.getPosts()

}