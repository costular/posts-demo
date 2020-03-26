package com.costular.postsdemo.presentation.postdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.costular.postsdemo.TestSchedulerProvider
import com.costular.postsdemo.domain.interactor.GetPostDetail
import com.costular.postsdemo.domain.model.PostDetail
import com.costular.postsdemo.domain.model.toPostId
import com.costular.postsdemo.util.network.SchedulerProvider
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PostDetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    val schedulers: SchedulerProvider = TestSchedulerProvider
    val getPostDetail: GetPostDetail = mockk(relaxed = true)

    lateinit var postDetailViewModel: PostDetailViewModel

    @Before
    fun setUp() {
        postDetailViewModel = PostDetailViewModel(schedulers, getPostDetail)
    }

    @Test
    fun `When load a post detail then should emit the data to livedata`() {
        // Given
        val postDetail: PostDetail = mockk(relaxed = true)
        every { getPostDetail.execute(any()) } returns Single.just(postDetail)

        // When
        postDetailViewModel.loadPost(1L.toPostId())

        // Then
        Truth.assertThat(postDetailViewModel.post.value).isEqualTo(postDetail)
    }

    @Test
    fun `When click close then should emit close event to livedata`() {
        // Given


        // When
        postDetailViewModel.close()

        // Then
        Truth.assertThat(postDetailViewModel.closeEvent.value!!.peekContent())
            .isEqualTo(Unit)
    }

}