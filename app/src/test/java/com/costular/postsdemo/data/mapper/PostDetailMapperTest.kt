package com.costular.postsdemo.data.mapper

import com.costular.postsdemo.data.model.CommentEntity
import com.costular.postsdemo.data.model.PostEntity
import com.costular.postsdemo.data.model.PostWithUserAndComments
import com.costular.postsdemo.data.model.UserEntity
import com.costular.postsdemo.domain.model.*
import com.google.common.truth.Truth
import org.junit.Test

class PostDetailMapperTest {

    private val userMapper = UserMapper()
    private val commentMapper = CommentMapper()
    val postDetailMapper = PostDetailMapper(userMapper, commentMapper)

    @Test
    fun `Given a PostWithUserAndComments when parsing should return a PostDetail with the same data`() {
        // Given
        val postWithUserAndComments = PostWithUserAndComments(
            PostEntity(
                3L,
                1L,
                "post description test"
            ),
            UserEntity(
                10L,
                "user name test",
                "username test",
                "avatar test"
            ),
            listOf(
                CommentEntity(
                    100L,
                    3L,
                    "name test",
                    "email test",
                    "body test"
                )
            )
        )
        val expected = PostDetail(
            3L.toPostId(),
            "post description test",
            User(
                10L.toUserId(),
                "user name test",
                "username test",
                "avatar test".toAvatar()
            ),
            listOf(
                Comment(
                    100L.toCommentId(),
                    3L.toPostId(),
                    "name test",
                    "email test",
                    "body test"
                )
            )
        )

        // When
        val actual = postDetailMapper.map(postWithUserAndComments)

        // Then
        Truth.assertThat(actual).isEqualTo(expected)
    }

}