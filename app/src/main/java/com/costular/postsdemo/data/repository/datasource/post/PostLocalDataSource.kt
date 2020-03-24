package com.costular.postsdemo.data.repository.datasource.post

import com.costular.postsdemo.data.model.CommentEntity
import com.costular.postsdemo.data.model.PostEntity
import com.costular.postsdemo.data.model.PostWithUserAndComments
import com.costular.postsdemo.data.model.UserEntity
import com.costular.postsdemo.domain.model.PostId
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface PostLocalDataSource {

    fun getPosts(): Flowable<List<PostEntity>>

    fun getPostDetail(postId: PostId): Single<PostWithUserAndComments>

    fun insertPosts(posts: List<PostEntity>): Completable

}

class PostLocalDataSourceImpl :
    PostLocalDataSource {

    override fun getPosts(): Flowable<List<PostEntity>> = Flowable.just(listOf()) // TODO

    override fun getPostDetail(postId: PostId): Single<PostWithUserAndComments> = TODO() //

    override fun insertPosts(posts: List<PostEntity>): Completable = Completable.complete()

}