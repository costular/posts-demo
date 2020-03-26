package com.costular.postsdemo.data.mapper

import com.costular.postsdemo.data.model.UserDTO
import com.costular.postsdemo.data.model.UserEntity
import com.costular.postsdemo.data.util.AvatarHelper
import com.costular.postsdemo.data.util.AvatarHelperImpl
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class UserEntityMapperTest {

    val avatarHelper: AvatarHelper = AvatarHelperImpl()

    lateinit var userEntityMapper: UserEntityMapper

    @Before
    fun setUp() {
        userEntityMapper = UserEntityMapper(avatarHelper)
    }

    @Test
    fun `Given a UserDTO when parsing should return a UserEntity with the same data`() {
        // Given
        val userDto = UserDTO(
            1L,
            "user's name test",
            "username test",
            "email test",
            "phone test",
            "website test"
        )
        val expected = UserEntity(
            1L,
            "user's name test",
            "username test",
            "http://api.adorable.io/avatars/1"
        )

        // When
        val actual = userEntityMapper.map(userDto)

        // Then
        Truth.assertThat(actual).isEqualTo(expected)
    }

}