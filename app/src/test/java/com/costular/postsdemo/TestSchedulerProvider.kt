package com.costular.postsdemo

import com.costular.postsdemo.util.network.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler

object TestSchedulerProvider : SchedulerProvider {
    override val io: Scheduler = Schedulers.trampoline()
    override val main: Scheduler = Schedulers.trampoline()
    override val computation: Scheduler = Schedulers.trampoline()
}