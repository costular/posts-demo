package com.costular.postsdemo.presentation.util

import com.costular.postsdemo.domain.PostRepository
import com.costular.postsdemo.domain.model.Outcome
import com.costular.postsdemo.domain.model.Post
import com.costular.postsdemo.domain.model.PostDetail
import com.costular.postsdemo.domain.model.PostId
import io.reactivex.Flowable
import io.reactivex.Single

class PostFakeEmptyRepositoryImpl : PostRepository {

    override fun observePosts(): Flowable<Outcome<List<Post>>> = Flowable.just(
        Outcome.Success(
            emptyList()
        )
    )

    override fun getPostDetail(postId: PostId): Single<PostDetail> = Single.never()

}