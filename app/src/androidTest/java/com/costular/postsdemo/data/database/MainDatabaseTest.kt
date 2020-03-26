package com.costular.postsdemo.data.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.costular.postsdemo.data.model.CommentEntity
import com.costular.postsdemo.data.model.PostEntity
import com.costular.postsdemo.data.model.PostWithUserAndComments
import com.costular.postsdemo.data.model.UserEntity
import com.google.common.truth.Truth
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MainDatabaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var postDao: PostDao
    lateinit var userDao: UserDao
    lateinit var commentDao: CommentDao

    lateinit var database: MainDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, MainDatabase::class.java).build()
        postDao = database.postDao()
        userDao = database.userDao()
        commentDao = database.commentDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        database.close()
    }

    @Test
    fun givenItemsWereInserted_whenFetchingPosts_thenShouldEmitThoseItems() {
        // Given
        val itemsInserted = listOf(
            PostEntity(
                1L,
                3L,
                "this is a title",
                "description test"
            )
        )

        // When
        val test = postDao.observePosts().test()
        postDao.insertOrUpdate(itemsInserted)

        // Then
        test.assertNoErrors()
            .assertNotComplete()
            .assertValues(
                emptyList(),
                itemsInserted
            )
    }

    @Test
    fun givenNoItemsWereInserted_whenFetchingPosts_thenShouldEmitEmptyList() {
        // Given


        // When
        val test = postDao.observePosts().test()

        // Then
        test.assertNoErrors()
            .assertNotComplete()
            .assertValue(emptyList())
    }

    @Test
    fun givenUsersAndCommentsExists_whenFetchingAPostDetail_thenShouldEmitTheItemAggregatedCorrectly() {
        // Given
        val post = PostEntity(
            1L,
            1L,
            "this is a title",
            "this is a sample post"
        )
        val user = UserEntity(
            post.userId,
            "user's name",
            "username",
            "https://avatar.com"
        )

        val comments = listOf(
            CommentEntity(
                1L,
                post.id,
                "whatever",
                "email",
                "this is a comment"
            ),
            CommentEntity(
                2L,
                post.id,
                "whatever",
                "email",
                "this is another comment"
            )
        )

        val expected = PostWithUserAndComments(
            post,
            user,
            comments
        )

        // When
        userDao.insertOrUpdate(user)
        commentDao.insertOrUpdate(comments)
        postDao.insertOrUpdate(post)

        val test = postDao.getPostById(1L).test()

        // Then
        test.assertNoErrors()
            .assertComplete()
            .assertValue(expected)
    }

}