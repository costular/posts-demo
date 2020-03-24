package com.costular.postsdemo.data.network

import io.reactivex.Scheduler

interface SchedulerProvider {
    val io: Scheduler
    val main: Scheduler
    val computation: Scheduler
}