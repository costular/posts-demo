package com.costular.postsdemo.presentation.util

import com.costular.postsdemo.util.network.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UiSchedulerProvider : SchedulerProvider {
    override val io: Scheduler = AndroidSchedulers.mainThread()
    override val main: Scheduler = AndroidSchedulers.mainThread()
    override val computation: Scheduler = AndroidSchedulers.mainThread()
}