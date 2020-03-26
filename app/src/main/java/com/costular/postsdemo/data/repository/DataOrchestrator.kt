package com.costular.postsdemo.data.repository

import com.costular.postsdemo.domain.model.Outcome
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers
import java.io.IOException

abstract class DataOrchestrator<Domain, Local, Remote> {

    fun fetch(): Flowable<Outcome<Domain>> {
        return Flowable.create<Outcome<Domain>>({ emitter ->
            retrieveLocal()
                .map<Outcome<Domain>> { Outcome.Success(returnMapper.invoke(it)) }
                .startWith(Outcome.Loading)
                .onErrorResumeNext { t: Throwable -> Flowable.just(Outcome.Failure(t)) }
                .subscribe { emitter.onNext(it) }

            retrieveRemote()
                .map { localMapper.invoke(it) }
                .doOnSuccess { onSaveLocally(it) }
                .subscribe(
                    {},
                    {
                        emitter.onNext(Outcome.Failure(it))
                    }
                )

        }, BackpressureStrategy.BUFFER)
    }

    abstract fun retrieveRemote(): Single<Remote>

    abstract fun retrieveLocal(): Flowable<Local>

    abstract fun onSaveLocally(result: Local)

    abstract val localMapper: (input: Remote) -> Local
    abstract val returnMapper: (input: Local) -> Domain


}