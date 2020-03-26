package com.costular.postsdemo.presentation.posts

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.costular.postsdemo.R
import com.costular.postsdemo.di.comments
import com.costular.postsdemo.di.users
import com.costular.postsdemo.presentation.util.appFake
import com.costular.postsdemo.presentation.util.mockedModule
import com.google.common.truth.Truth
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
        loadKoinModules(
            listOf(
                appFake,
                mockedModule, comments, users
            )
        )
    }

    @Test
    fun testFirstElementShowsCorrectly() {
        launchFragmentInContainer<PostsFragment>(themeResId = R.style.AppTheme)

        posts {
            check {
                hasSize(2)
                checkPositionHasTitle(1, "title test")
                checkPositionHasDescription(1, "description test")
            }
        }
    }

    @Test
    fun testNavigateToPostDetail() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        navController.setGraph(R.navigation.nav_graph)

        launchFragmentInContainer<PostsFragment>(themeResId = R.style.AppTheme).onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        posts {
            clickAtPosition(1)
        }

        Truth.assertThat(navController.currentDestination?.id).isEqualTo(R.id.postDetailFragment)
    }

}
