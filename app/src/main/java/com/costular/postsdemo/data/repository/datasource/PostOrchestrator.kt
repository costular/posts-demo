package com.costular.postsdemo.data.repository.datasource

import com.costular.postsdemo.data.mapper.*
import com.costular.postsdemo.data.model.PostDTO
import com.costular.postsdemo.data.model.PostEntity
import com.costular.postsdemo.data.repository.DataOrchestrator
import com.costular.postsdemo.data.repository.datasource.comment.CommentLocalDataSource
import com.costular.postsdemo.data.repository.datasource.comment.CommentRemoteDataSource
import com.costular.postsdemo.data.repository.datasource.post.PostLocalDataSource
import com.costular.postsdemo.data.repository.datasource.post.PostRemoteDataSource
import com.costular.postsdemo.data.repository.datasource.user.UserLocalDataSource
import com.costular.postsdemo.data.repository.datasource.user.UserRemoteDataSource
import com.costular.postsdemo.domain.model.Post
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class PostOrchestrator(
    private val postRemote: PostRemoteDataSource,
    private val postLocal: PostLocalDataSource,
    private val postMapper: PostMapper,
    private val postEntityMapper: PostEntityMapper,
    private val userRemote: UserRemoteDataSource,
    private val userLocal: UserLocalDataSource,
    private val userEntityMapper: UserEntityMapper,
    private val commentsRemote: CommentRemoteDataSource,
    private val commentsLocal: CommentLocalDataSource,
    private val commentEntityMapper: CommentEntityMapper
) : DataOrchestrator<List<Post>, List<PostEntity>, List<PostDTO>>() {

    override val localMapper: (input: List<PostDTO>) -> List<PostEntity> = {
        postEntityMapper.mapList(it)
    }
    override val returnMapper: (input: List<PostEntity>) -> List<Post> = {
        postMapper.mapList(it)
    }

    override fun retrieveRemote(): Single<List<PostDTO>> =
        userRemote.getUsers()
            .map(userEntityMapper::mapList)
            .doOnSuccess { userLocal.insertUsers(it) }
            .flatMap { commentsRemote.getComments() }
            .map(commentEntityMapper::mapList)
            .doOnSuccess { commentsLocal.insertComments(it) }
            .flatMap { postRemote.getPosts() }

    override fun retrieveLocal(): Flowable<List<PostEntity>> = postLocal.observePosts()

    override fun onSaveLocally(result: List<PostEntity>): Completable = postLocal.insertPosts(result)

}