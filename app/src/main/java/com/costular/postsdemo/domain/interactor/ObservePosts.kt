package com.costular.postsdemo.domain.interactor

import com.costular.postsdemo.domain.PostRepository
import com.costular.postsdemo.domain.model.Outcome
import com.costular.postsdemo.domain.model.Post
import io.reactivex.Flowable

class ObservePosts(
    private val postRepository: PostRepository
) : UseCase<Flowable<Outcome<List<Post>>>, UseCase.None>() {

    override fun build(params: None): Flowable<Outcome<List<Post>>> =
        postRepository.observePosts()

}