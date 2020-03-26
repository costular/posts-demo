package com.costular.postsdemo.data.orchestrator

import com.costular.postsdemo.data.repository.DataOrchestrator
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class TestDataOrchestrator(
    private val remote: Single<TestDTO>,
    private val local: Flowable<TestEntity>,
    private val persister: (TestEntity) -> Unit
) : DataOrchestrator<Test, TestEntity, TestDTO>() {

    override val localMapper: (input: TestDTO) -> TestEntity = { TestEntity(it.id) }
    override val returnMapper: (input: TestEntity) -> Test = { Test(it.id) }

    override fun retrieveRemote(): Single<TestDTO> = remote

    override fun retrieveLocal(): Flowable<TestEntity> = local

    override fun onSaveLocally(result: TestEntity) = persister(result)

}