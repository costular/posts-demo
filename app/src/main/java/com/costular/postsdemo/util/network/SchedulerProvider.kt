package com.costular.postsdemo.util.network

import io.reactivex.Scheduler

interface SchedulerProvider {
    val io: Scheduler
    val main: Scheduler
    val computation: Scheduler
}