package com.costular.postsdemo.domain

import com.costular.postsdemo.domain.interactor.GetPostDetail
import com.costular.postsdemo.domain.model.toPostId
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class GetPostDetailTest {

    val repository: PostRepository = mockk(relaxed = true)

    lateinit var getPostDetail: GetPostDetail

    @Before
    fun setUp() {
        getPostDetail = GetPostDetail(repository)
    }

    @Test
    fun `When get post detail should call post repository`() {
        // Given
        val params = GetPostDetail.Params(1L.toPostId())

        // When
        getPostDetail.execute(params)

        // Then
        verify { repository.getPostDetail(params.id) }
    }

}