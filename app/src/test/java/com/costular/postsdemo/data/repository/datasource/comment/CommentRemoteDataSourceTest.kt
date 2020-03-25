package com.costular.postsdemo.data.repository.datasource.comment

import com.costular.postsdemo.data.model.CommentDTO
import com.costular.postsdemo.data.net.CommentApi
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class CommentRemoteDataSourceTest {

    val commentApi: CommentApi = mockk()

    lateinit var commentRemote: CommentRemoteDataSource

    @Before
    fun setUp() {
        commentRemote = CommentRemoteDataSourceImpl(commentApi)
    }

    @Test
    fun `When get comments then should call comments api`() {
        // Given
        val comments = listOf(
            CommentDTO(
                1L,
                1L,
                "name",
                "email",
                "a sample body"
            )
        )
        every { commentApi.getComments() } returns Single.just(comments)

        // When
        val test = commentRemote.getComments().test()

        // Then
        test.assertValue(comments)
        verify { commentApi.getComments() }
    }

}