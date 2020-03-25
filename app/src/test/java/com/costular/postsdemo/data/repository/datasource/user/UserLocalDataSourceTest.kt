package com.costular.postsdemo.data.repository.datasource.user

import com.costular.postsdemo.data.database.UserDao
import com.costular.postsdemo.data.model.UserEntity
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class UserLocalDataSourceTest {

    val userDao: UserDao = mockk(relaxed = true)

    lateinit var userLocal: UserLocalDataSource

    @Before
    fun setUp() {
        userLocal = UserLocalDataSourceImpl(userDao)
    }

    @Test
    fun `When insert users then should call user dao`() {
        // Given
        val users = listOf(
            UserEntity(
                1L,
                "name",
                "username",
                "avatar"
            )
        )

        // When
        userLocal.insertUsers(users)

        // Then
        verify { userDao.insertOrUpdate(users) }
    }

}