package com.costular.postsdemo.data.repository

import com.costular.postsdemo.data.mapper.PostDetailMapper
import com.costular.postsdemo.data.repository.datasource.PostOrchestrator
import com.costular.postsdemo.data.repository.datasource.post.PostLocalDataSource
import com.costular.postsdemo.domain.PostRepository
import com.costular.postsdemo.domain.model.Outcome
import com.costular.postsdemo.domain.model.Post
import com.costular.postsdemo.domain.model.PostDetail
import com.costular.postsdemo.domain.model.PostId
import io.reactivex.Flowable
import io.reactivex.Single

class PostRepositoryImpl(
    private val postOrchestrator: PostOrchestrator,
    private val postLocalDataSource: PostLocalDataSource,
    private val postDetailMapper: PostDetailMapper
) : PostRepository {

    override fun getPosts(): Flowable<Outcome<List<Post>>> = postOrchestrator.fetch()

    override fun getPostDetail(postId: PostId): Single<PostDetail> =
        postLocalDataSource.getPostDetail(postId)
            .map(postDetailMapper::map)

}