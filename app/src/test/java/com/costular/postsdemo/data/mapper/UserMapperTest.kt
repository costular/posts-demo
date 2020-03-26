package com.costular.postsdemo.data.mapper

import com.costular.postsdemo.data.model.UserEntity
import com.costular.postsdemo.domain.model.User
import com.costular.postsdemo.domain.model.toAvatar
import com.costular.postsdemo.domain.model.toUserId
import com.google.common.truth.Truth
import org.junit.Test

class UserMapperTest {

    val userMapper = UserMapper()

    @Test
    fun `Given a UserEntity when parsing should return a User with the same data`() {
        // Given
        val userEntity = UserEntity(
            1L,
            "user's name",
            "username",
            "avatar"
        )
        val expected = User(
            1L.toUserId(),
            "user's name",
            "username",
            "avatar".toAvatar()
        )

        // When
        val actual = userMapper.map(userEntity)

        // Then
        Truth.assertThat(actual).isEqualTo(expected)
    }
}