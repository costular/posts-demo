package com.costular.postsdemo.data.mapper

import com.costular.postsdemo.data.model.CommentDTO
import com.costular.postsdemo.data.model.CommentEntity
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class CommentEntityMapperTest {

    private val commentEntityMapper = CommentEntityMapper()

    @Test
    fun `Given a CommentDTO when parsing should return a CommentEntity with the same data`() {
        // Given
        val commentDTO = CommentDTO(
            1L,
            10L,
            "name test",
            "email test",
            "body test"
        )
        val expected = CommentEntity(
            1L,
            10L,
            "name test",
            "email test",
            "body test"
        )

        // When
        val actual = commentEntityMapper.map(commentDTO)

        // Then
        assertThat(actual).isEqualTo(expected)
    }

}