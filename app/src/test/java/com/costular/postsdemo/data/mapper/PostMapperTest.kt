package com.costular.postsdemo.data.mapper

import com.costular.postsdemo.data.model.PostEntity
import com.costular.postsdemo.domain.model.Post
import com.costular.postsdemo.domain.model.toPostId
import com.google.common.truth.Truth
import org.junit.Test

class PostMapperTest {

    val postMapper = PostMapper()

    @Test
    fun `Given a PostEntity when parsing should return a Post with the same data`() {
        // Given
        val postEntity = PostEntity(
            1L,
            1L,
            "post title",
            "post description"
        )
        val expected = Post(
            1L.toPostId(),
            "post title",
            "post description"
        )

        // When
        val actual = postMapper.map(postEntity)

        // Then
        Truth.assertThat(actual).isEqualTo(expected)
    }

}