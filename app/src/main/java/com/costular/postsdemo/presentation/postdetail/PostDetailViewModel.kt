package com.costular.postsdemo.presentation.postdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.costular.postsdemo.domain.interactor.GetPostDetail
import com.costular.postsdemo.domain.model.PostDetail
import com.costular.postsdemo.domain.model.PostId
import com.costular.postsdemo.util.Event
import com.costular.postsdemo.util.RxViewModel
import com.costular.postsdemo.util.extensions.call
import com.costular.postsdemo.util.network.SchedulerProvider
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class PostDetailViewModel(
    private val schedulers: SchedulerProvider,
    private val getPostDetail: GetPostDetail
) : RxViewModel() {

    private val _post = MutableLiveData<PostDetail>()
    val post: LiveData<PostDetail>
        get() = _post

    private val _closeEvent = MutableLiveData<Event<Unit>>()
    val closeEvent: LiveData<Event<Unit>>
        get() = _closeEvent

    fun loadPost(id: PostId) {
        getPostDetail.execute(GetPostDetail.Params(id))
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.main)
            .subscribeBy(
                onError = {

                },
                onSuccess = { post ->
                    _post.value = post
                }
            )
            .addTo(compositeDisposable)
    }

    fun close() {
        _closeEvent.call()
    }

}