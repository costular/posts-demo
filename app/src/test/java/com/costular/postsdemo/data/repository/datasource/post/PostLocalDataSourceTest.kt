package com.costular.postsdemo.data.repository.datasource.post

import com.costular.postsdemo.data.database.PostDao
import com.costular.postsdemo.data.model.PostEntity
import com.costular.postsdemo.data.model.PostWithUserAndComments
import com.costular.postsdemo.data.model.UserEntity
import com.costular.postsdemo.domain.model.toPostId
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class PostLocalDataSourceTest {

    private val postDao: PostDao = mockk(relaxed = true)

    lateinit var postLocalDataSource: PostLocalDataSource

    @Before
    fun setUp() {
        postLocalDataSource = PostLocalDataSourceImpl(postDao)
    }

    @Test
    fun `When observing posts then should call post dao`() {
        // Given
        val posts = listOf(
            PostEntity(
                1L,
                1L,
                "whatever"
            )
        )
        every { postDao.observePosts() } returns Flowable.just(posts)

        // When
        val test = postLocalDataSource.observePosts().test()

        // Then
        test.assertValue(posts)
        verify { postDao.observePosts() }
    }

    @Test
    fun `When get post deail then should call post dao`() {
        // Given
        val postId = 1L
        val post = PostWithUserAndComments(
            PostEntity(
                postId,
                1L,
                "whatever"
            ),
            UserEntity(
                1L,
                "name",
                "username",
                "avatar"
            ),
            emptyList()
        )
        every { postDao.getPostById(postId) } returns Single.just(post)

        // When
        val test = postLocalDataSource.getPostDetail(postId.toPostId()).test()

        // Then
        test.assertValue(post)
        verify { postDao.getPostById(postId) }
    }

    @Test
    fun `When insert posts then should call post dao`() {
        // Given
        val posts = listOf(
            PostEntity(
                1L,
                1L,
                "whatever"
            )
        )

        // When
        postLocalDataSource.insertPosts(posts)

        // Then
        verify { postDao.insertOrUpdate(posts) }
    }

}