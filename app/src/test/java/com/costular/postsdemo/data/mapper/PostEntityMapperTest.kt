package com.costular.postsdemo.data.mapper

import com.costular.postsdemo.data.model.PostDTO
import com.costular.postsdemo.data.model.PostEntity
import com.google.common.truth.Truth
import org.junit.Test

class PostEntityMapperTest {

    val postEntityMapper = PostEntityMapper()

    @Test
    fun `Given a PostDTO when parsing should return a PostEntity with the same data`() {
        // Given
        val postEntity = PostDTO(
            1L,
            1L,
            "post title",
            "post description test"
        )
        val expected = PostEntity(
            1L,
            1L,
            "post title",
            "post description test"
        )

        // When
        val actual = postEntityMapper.map(postEntity)

        // Then
        Truth.assertThat(actual).isEqualTo(expected)
    }

}