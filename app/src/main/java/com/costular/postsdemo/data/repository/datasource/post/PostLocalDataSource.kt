package com.costular.postsdemo.data.repository.datasource.post

import com.costular.postsdemo.data.database.PostDao
import com.costular.postsdemo.data.model.CommentEntity
import com.costular.postsdemo.data.model.PostEntity
import com.costular.postsdemo.data.model.PostWithUserAndComments
import com.costular.postsdemo.data.model.UserEntity
import com.costular.postsdemo.domain.model.PostId
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface PostLocalDataSource {

    fun observePosts(): Flowable<List<PostEntity>>

    fun getPostDetail(postId: PostId): Single<PostWithUserAndComments>

    fun insertPosts(posts: List<PostEntity>): Completable

}

class PostLocalDataSourceImpl(private val postDao: PostDao) : PostLocalDataSource {

    override fun observePosts(): Flowable<List<PostEntity>> = postDao.observePosts()

    override fun getPostDetail(postId: PostId): Single<PostWithUserAndComments> =
        postDao.getPostById(postId.toLong())

    override fun insertPosts(posts: List<PostEntity>): Completable =
        postDao.insertOrUpdate(posts)

}