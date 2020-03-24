package com.costular.postsdemo.domain

import com.costular.postsdemo.data.repository.PostRepositoryImpl
import com.costular.postsdemo.data.repository.datasource.PostOrchestrator
import com.costular.postsdemo.domain.model.Outcome
import com.costular.postsdemo.domain.model.Post
import com.costular.postsdemo.domain.model.PostId
import io.mockk.every
import io.mockk.mockk
import io.reactivex.processors.PublishProcessor
import org.junit.Before
import org.junit.Test

class PostRepositoryTest {

    private val postOrchestrator: PostOrchestrator = mockk()

    lateinit var postRepository: PostRepository

    @Before
    fun setUp() {
        postRepository = PostRepositoryImpl(postOrchestrator)
    }

    @Test
    fun `When get the posts then should return them from local data source`() {
        // Given
        val posts = listOf(
            Post(PostId(1L), "test1"),
            Post(PostId(2L), "test2")
        )
        val orchestratorStream = PublishProcessor.create<Outcome<List<Post>>>()
        every { postOrchestrator.fetch() } returns orchestratorStream

        // When
        val test = postRepository.getPosts().test()
        orchestratorStream.run {
            onNext(Outcome.Loading)
            onNext(Outcome.Success(posts))
        }


        // Then
        test.assertNotComplete()
            .assertNoErrors()
            .assertValues(
                Outcome.Loading,
                Outcome.Success(posts)
            )
    }

}