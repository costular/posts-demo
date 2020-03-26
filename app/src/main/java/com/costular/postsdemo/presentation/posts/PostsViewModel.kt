package com.costular.postsdemo.presentation.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.costular.postsdemo.domain.interactor.ObservePosts
import com.costular.postsdemo.domain.interactor.execute
import com.costular.postsdemo.domain.model.Outcome
import com.costular.postsdemo.domain.model.Post
import com.costular.postsdemo.domain.model.PostId
import com.costular.postsdemo.util.Event
import com.costular.postsdemo.util.RxViewModel
import com.costular.postsdemo.util.extensions.call
import com.costular.postsdemo.util.network.SchedulerProvider
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class PostsViewModel(
    private val schedulers: SchedulerProvider,
    private val observePosts: ObservePosts
) : RxViewModel() {

    private val _posts = MutableLiveData<Outcome<List<Post>>>()
    val posts: LiveData<Outcome<List<Post>>>
        get() = _posts

    private val _openPostDetailEvent = MutableLiveData<Event<PostId>>()
    val openPostDetailEvent: LiveData<Event<PostId>>
        get() = _openPostDetailEvent

    fun observePosts() {
        observePosts
            .execute()
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.main)
            .filter { it !is Outcome.Failure }
            .subscribeBy(
                onError = {
                    // This won't be called since we're wrapping the exceptions but just in case
                },
                onNext = {
                    _posts.value = it
                }
            )
            .addTo(compositeDisposable)
    }

    fun clickOnPost(post: Post) {
        _openPostDetailEvent.call(post.id)
    }

}