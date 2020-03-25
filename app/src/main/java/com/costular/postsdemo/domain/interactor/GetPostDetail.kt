package com.costular.postsdemo.domain.interactor

import com.costular.postsdemo.domain.PostRepository
import com.costular.postsdemo.domain.model.PostDetail
import com.costular.postsdemo.domain.model.PostId
import io.reactivex.Single

class GetPostDetail(
    private val postRepository: PostRepository
) : UseCase<Single<PostDetail>, GetPostDetail.Params>() {

    override fun build(params: Params): Single<PostDetail> = postRepository.getPostDetail(params.id)

    data class Params(val id: PostId)
}
