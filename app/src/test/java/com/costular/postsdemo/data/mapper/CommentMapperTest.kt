package com.costular.postsdemo.data.mapper

import com.costular.postsdemo.data.model.CommentEntity
import com.costular.postsdemo.domain.model.Comment
import com.costular.postsdemo.domain.model.toCommentId
import com.costular.postsdemo.domain.model.toPostId
import com.google.common.truth.Truth
import org.junit.Test

class CommentMapperTest {

    val commentMapper = CommentMapper()

    @Test
    fun `Given a CommentEntity when parsing should return a Comment with the same data`() {
        // Given
        val commentEntity = CommentEntity(
            1L,
            1L,
            "name test",
            "email test",
            "body test"
        )
        val expected = Comment(
            1L.toCommentId(),
            1L.toPostId(),
            "name test",
            "email test",
            "body test"
        )

        // When
        val actual = commentMapper.map(commentEntity)

        // Then
        Truth.assertThat(actual).isEqualTo(expected)
    }
}