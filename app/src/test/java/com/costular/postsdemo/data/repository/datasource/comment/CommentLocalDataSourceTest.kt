package com.costular.postsdemo.data.repository.datasource.comment

import com.costular.postsdemo.data.database.CommentDao
import com.costular.postsdemo.data.model.CommentEntity
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CommentLocalDataSourceTest {

    val commentDao: CommentDao = mockk(relaxed = true)

    lateinit var commentLocal: CommentLocalDataSource

    @Before
    fun setUp() {
        commentLocal = CommentLocalDataSourceImpl(commentDao)
    }

    @Test
    fun `When insert comments then should call comment dao`() {
        // Given
        val comments = listOf(
            CommentEntity(
                1L,
                1L,
                "name",
                "a sample email",
                "a body"
            )
        )

        // When
        commentLocal.insertComments(comments)

        // Then
        verify { commentDao.insertOrUpdate(comments) }
    }

}