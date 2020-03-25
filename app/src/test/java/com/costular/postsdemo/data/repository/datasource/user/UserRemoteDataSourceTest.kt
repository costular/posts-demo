package com.costular.postsdemo.data.repository.datasource.user

import com.costular.postsdemo.data.model.UserDTO
import com.costular.postsdemo.data.net.UserApi
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class UserRemoteDataSourceTest {

    val userApi: UserApi = mockk()

    lateinit var userRemote: UserRemoteDataSource

    @Before
    fun setUp() {
        userRemote = UserRemoteDataSourceImpl(userApi)
    }

    @Test
    fun `When get users then should call user api`() {
        // Given
        val users = listOf(
            UserDTO(
                1L,
                "name",
                "username",
                "email",
                "phone",
                "website"
            )
        )
        every { userApi.getUsers() } returns Single.just(users)

        // When
        val test = userRemote.getUsers().test()

        // Then
        test.assertValue(users)
        verify { userApi.getUsers() }
    }

}