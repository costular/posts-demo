package com.costular.postsdemo.presentation.posts

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.costular.postsdemo.di.comments
import com.costular.postsdemo.di.users
import com.costular.postsdemo.presentation.util.onFragment
import com.costular.postsdemo.presentation.robots.posts
import com.costular.postsdemo.presentation.util.appFake
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
@MediumTest
class PostsFragmentTest : KoinTest {

    @Before
    fun setUp() {
        loadKoinModules(listOf(appFake, mockedModule, comments, users))
    }

    @Test
    fun testFirstElementShowsCorrectly() {
        onFragment<PostsFragment> {
            posts {
                send {
                    hasSize(2)
                    checkPositionHasTitle(1, "title test")
                    checkPositionHasDescription(1, "description test")
                }
            }
        }
    }

}
