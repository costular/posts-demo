package com.costular.postsdemo.presentation.posts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.costular.postsdemo.TestSchedulerProvider
import com.costular.postsdemo.domain.interactor.ObservePosts
import com.costular.postsdemo.domain.model.Outcome
import com.costular.postsdemo.domain.model.Post
import com.costular.postsdemo.domain.model.toPostId
import com.costular.postsdemo.util.Event
import com.costular.postsdemo.util.network.SchedulerProvider
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PostsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    val schedulers: SchedulerProvider = TestSchedulerProvider
    val observePosts: ObservePosts = mockk(relaxed = true)

    lateinit var postsViewModel: PostsViewModel

    @Before
    fun setUp() {
        postsViewModel = PostsViewModel(schedulers, observePosts)
    }

    @Test
    fun `Given loading when observing posts then should emit loading to livedata`() {
        // Given
        val loading = Outcome.Loading
        every { observePosts.execute(any()) } returns Flowable.just(loading)

        // When
        postsViewModel.observePosts()

        // Then
        val actual = postsViewModel.posts.value

        Truth.assertThat(actual).isEqualTo(loading)
    }

    @Test
    fun `Given some values are emitted when observing posts then should emit those same values to livedata`() {
        // Given
        val items = PublishProcessor.create<Outcome<List<Post>>>()
        every { observePosts.execute(any()) } returns items

        // When
        postsViewModel.observePosts()

        // Then
        val loading = Outcome.Loading
        items.onNext(loading)
        Truth.assertThat(postsViewModel.posts.value).isEqualTo(loading)

        val posts = Outcome.Success(
            listOf(
                Post(1L.toPostId(), "a title", "a sample description")
            )
        )
        items.onNext(posts)
        Truth.assertThat(postsViewModel.posts.value).isEqualTo(posts)
    }

    @Test
    fun `When a post is clicked then should emit the event`() {
        // Given
        val post = Post(10L.toPostId(), "some title", "some description")

        // When
        postsViewModel.clickOnPost(post)

        // Then
        Truth.assertThat(postsViewModel.openPostDetailEvent.value!!.peekContent())
            .isEqualTo(post.id)
    }

}