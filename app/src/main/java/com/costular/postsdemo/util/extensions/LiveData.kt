package com.costular.postsdemo.util.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.costular.postsdemo.util.Event

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) {
    liveData.observe(this, Observer {
        it?.let(body)
    })
}

fun MutableLiveData<Event<Unit>>.call() {
    this.value = Event(Unit)
}

fun <T> MutableLiveData<Event<T>>.call(value: T) {
    this.value = Event(value)
}

fun MutableLiveData<Event<Unit>>.handle(listener: () -> Unit) {
    value?.getContentIfNotHandled()?.let { listener() }
}