package com.costular.postsdemo.data.orchestrator

import com.costular.postsdemo.domain.model.Outcome
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.processors.PublishProcessor
import io.reactivex.subjects.PublishSubject
import org.junit.Test
import java.io.IOException
import java.sql.SQLException

class DataOrchestratorTest {

    @Test
    fun `when fetching should emit loading event`() {
        // Given
        val persister: (TestEntity) -> Unit = mockk()

        val testDataOrchestrator = TestDataOrchestrator(
            Single.just(TestDTO(1L)),
            Flowable.just(TestEntity(1L)),
            persister
        )

        // When
        val test = testDataOrchestrator.fetch().test()

        // Then
        test.assertNotComplete()
            .assertNoErrors()
            .assertValueAt(0, Outcome.Loading)
    }

    @Test
    fun `when fetching should emit loading and success afterwards`() {
        // Given
        val id = 10L
        val persister: (TestEntity) -> Unit = {  }

        val testDataOrchestrator = TestDataOrchestrator(
            Single.just(TestDTO(id)),
            Flowable.just(TestEntity(10L)),
            persister
        )

        // When
        val test = testDataOrchestrator.fetch().test()

        // Then
        test.assertNotComplete()
            .assertNoErrors()
            .assertValues(
                Outcome.Loading,
                Outcome.Success(Test(id))
            )
    }

    @Test
    fun `given a network error is thrown when fetching should emit an error`() {
        // Given
        val id = 11L
        val persister: (TestEntity) -> Unit = mockk()
        val exception = IOException("something went wrong")

        val testDataOrchestrator = TestDataOrchestrator(
            Single.error(exception),
            Flowable.just(TestEntity(id)),
            persister
        )

        // When
        val test = testDataOrchestrator.fetch().test()

        // Then
        test.assertNotComplete()
            .assertNoErrors()
            .assertValues(
                Outcome.Loading,
                Outcome.Success(Test(id)),
                Outcome.Failure(exception)
            )
    }

    @Test
    fun `Given network throws an exception and db succeed when fetching then should emit an error and success afterwards`() {
        // Given
        val id = 12L
        val secondId = 13L
        val exception = IOException("something went wrong")

        val local = PublishProcessor.create<TestEntity>()
        val remote = PublishSubject.create<TestDTO>()
        val persister: (TestEntity) -> Unit = mockk()

        val testDataOrchestrator = TestDataOrchestrator(
            remote.firstOrError(),
            local,
            persister
        )

        // When
        val test = testDataOrchestrator.fetch().test()
        local.onNext(TestEntity(id))
        remote.onError(exception)
        local.onNext(TestEntity(secondId))

        // Then
        test.assertNotComplete()
            .assertNoErrors()
            .assertValues(
                Outcome.Loading,
                Outcome.Success(Test(id)),
                Outcome.Failure(exception),
                Outcome.Success(Test(secondId))
            )
    }

    @Test
    fun `Given network succeed when fetching then should emit remote value through local stream`() {
        // Given
        val id = 14L
        val remoteId = 15L

        val local = PublishProcessor.create<TestEntity>()
        val remote = PublishSubject.create<TestDTO>()
        val persister: (TestEntity) -> Unit = { local.onNext(TestEntity(it.id)) }

        val testDataOrchestrator = TestDataOrchestrator(
            remote.firstOrError(),
            local,
            persister
        )

        // When
        val test = testDataOrchestrator.fetch().test()
        local.onNext(TestEntity(id))
        remote.onNext(TestDTO(remoteId))

        // Then
        test.assertNotComplete()
            .assertNoErrors()
            .assertValues(
                Outcome.Loading,
                Outcome.Success(Test(id)),
                Outcome.Success(Test(remoteId))
            )
    }

    @Test
    fun `Given both sources throw an exception when fetching then should emit two failures`() {
        // Given
        val id = 16L
        val networkException = IOException("something went wrong")
        val localException = SQLException("something went wrong with the database")

        val persister: (TestEntity) -> Unit = mockk()

        val testDataOrchestrator = TestDataOrchestrator(
            Single.error(networkException),
            Flowable.error(localException),
            persister
        )

        // When
        val test = testDataOrchestrator.fetch().test()

        // Then
        test.assertNotComplete()
            .assertNoErrors()
            .assertValues(
                Outcome.Loading,
                Outcome.Failure(localException),
                Outcome.Failure(networkException)
            )

        verify(exactly = 0) { persister(any()) }
    }

    @Test
    fun `Given the local gets updated more than once when fetching then should emit all those data`() {
        // Given
        val id = 17L
        val remoteId = 18L
        val secondId = 19L

        val local = PublishProcessor.create<TestEntity>()
        val remote = PublishSubject.create<TestDTO>()
        val persister: (TestEntity) -> Unit = { local.onNext(TestEntity(it.id)) }

        val testDataOrchestrator = TestDataOrchestrator(
            remote.firstOrError(),
            local,
            persister
        )

        // When
        val test = testDataOrchestrator.fetch().test()
        local.onNext(TestEntity(id))
        remote.onNext(TestDTO(remoteId))
        local.onNext(TestEntity(secondId))

        // Then
        test.assertNotComplete()
            .assertNoErrors()
            .assertValues(
                Outcome.Loading,
                Outcome.Success(Test(id)),
                Outcome.Success(Test(remoteId)),
                Outcome.Success(Test(secondId))
            )
    }

}