package com.costular.postsdemo.presentation.postdetail

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.costular.postsdemo.R
import com.costular.postsdemo.di.comments
import com.costular.postsdemo.di.users
import com.costular.postsdemo.presentation.util.appFake
import com.costular.postsdemo.presentation.util.mockedModule
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class PostDetailFragmentTest : KoinTest {

    @Before
    fun setUp() {
        loadKoinModules(
            listOf(
                appFake,
                mockedModule,
                comments,
                users
            )
        )
    }

    @Test
    fun testInfoShowsCorrectly() {
        val bundle = Bundle().apply { putLong("id", 1L) }
        launchFragmentInContainer<PostDetailFragment>(bundle, R.style.AppTheme)

        postDetail {
            check {
                isTitle("This is a post")
                isBody("This is the post's description")
                isAuthorName("user's name")
                isCommentCount(1)
            }
        }
    }

}