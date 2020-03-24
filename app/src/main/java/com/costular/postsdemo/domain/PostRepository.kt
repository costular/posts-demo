package com.costular.postsdemo.domain

import com.costular.postsdemo.domain.model.Outcome
import com.costular.postsdemo.domain.model.Post
import com.costular.postsdemo.domain.model.PostDetail
import com.costular.postsdemo.domain.model.PostId
import io.reactivex.Flowable
import io.reactivex.Single

interface PostRepository {

    fun getPosts(): Flowable<Outcome<List<Post>>>
    fun getPostDetail(postId: PostId): Single<Outcome<PostDetail>>

}