package com.costular.postsdemo.data.repository

import com.costular.postsdemo.data.model.PostDTO
import com.costular.postsdemo.data.net.PostApi
import com.costular.postsdemo.data.repository.datasource.post.PostRemoteDataSource
import com.costular.postsdemo.data.repository.datasource.post.PostRemoteDataSourceImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class PostRemoteDataSourceTest {

    val postApi: PostApi = mockk()

    lateinit var postRemote: PostRemoteDataSource

    @Before
    fun setUp() {
        postRemote = PostRemoteDataSourceImpl(postApi)
    }

    @Test
    fun `When fetching posts remote then should call api`() {
        // Given
        val posts = listOf(
            PostDTO(
                1L,
                1L,
                "a sample title",
                "a sample body"
            )
        )
        every { postApi.getPosts() } returns Single.just(posts)

        // When
        val test = postRemote.getPosts().test()

        // Then
        test.assertValue(posts)
        verify { postApi.getPosts() }
    }

}