package com.costular.postsdemo

import com.costular.postsdemo.data.network.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider(private val testScheduler: TestScheduler) : SchedulerProvider {
    override val io: Scheduler = testScheduler
    override val main: Scheduler = testScheduler
    override val computation: Scheduler = testScheduler
}