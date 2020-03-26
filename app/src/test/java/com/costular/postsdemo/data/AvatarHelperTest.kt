package com.costular.postsdemo.data

import com.costular.postsdemo.data.util.AvatarHelper
import com.costular.postsdemo.data.util.AvatarHelperImpl
import com.costular.postsdemo.domain.model.UserId
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class AvatarHelperTest {

    lateinit var avatarHelperTest: AvatarHelper

    @Before
    fun setUp() {
        avatarHelperTest = AvatarHelperImpl()
    }

    @Test
    fun `Given a user id when parsing then should return the correct url`() {
        // Given
        val userId = UserId(10L)

        // When
        val actual = avatarHelperTest.getAvatarUrl(userId)

        // Then
        val expected = "http://api.adorable.io/avatars/${userId.userId}"
        assertThat(actual).isEqualTo(expected)
    }

}