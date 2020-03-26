package com.costular.postsdemo.domain

import com.costular.postsdemo.domain.interactor.ObservePosts
import com.costular.postsdemo.domain.interactor.execute
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ObservePostsTest {

    val postRepository: PostRepository = mockk(relaxed = true)

    lateinit var observePosts: ObservePosts

    @Before
    fun setUp() {
        observePosts =
            ObservePosts(postRepository)
    }

    @Test
    fun `When observe posts should call the repository`() {
        // Given

        // When
        observePosts.execute()

        // Then
        verify { postRepository.observePosts() }
    }

}