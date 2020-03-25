package com.costular.postsdemo.data.repository

import com.costular.postsdemo.data.mapper.CommentMapper
import com.costular.postsdemo.data.mapper.PostDetailMapper
import com.costular.postsdemo.data.mapper.UserMapper
import com.costular.postsdemo.data.model.CommentEntity
import com.costular.postsdemo.data.model.PostEntity
import com.costular.postsdemo.data.model.PostWithUserAndComments
import com.costular.postsdemo.data.model.UserEntity
import com.costular.postsdemo.data.repository.datasource.PostOrchestrator
import com.costular.postsdemo.data.repository.datasource.post.PostLocalDataSource
import com.costular.postsdemo.domain.PostRepository
import com.costular.postsdemo.domain.model.*
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.processors.PublishProcessor
import org.junit.Before
import org.junit.Test

class PostRepositoryTest {

    private val userMapper = UserMapper()
    private val commentMapper = CommentMapper()
    private val postDetailMapper = PostDetailMapper(userMapper, commentMapper)

    private val postOrchestrator: PostOrchestrator = mockk()
    private val postLocalDataSource: PostLocalDataSource = mockk()

    lateinit var postRepository: PostRepository

    @Before
    fun setUp() {
        postRepository = PostRepositoryImpl(
            postOrchestrator,
            postLocalDataSource,
            postDetailMapper
        )
    }

    @Test
    fun `When get the posts then should return them from local data source`() {
        // Given
        val posts = listOf(
            Post(PostId(1L), "test1"),
            Post(PostId(2L), "test2")
        )
        val orchestratorStream = PublishProcessor.create<Outcome<List<Post>>>()
        every { postOrchestrator.fetch() } returns orchestratorStream

        // When
        val test = postRepository.observePosts().test()
        orchestratorStream.run {
            onNext(Outcome.Loading)
            onNext(Outcome.Success(posts))
        }


        // Then
        test.assertNotComplete()
            .assertNoErrors()
            .assertValues(
                Outcome.Loading,
                Outcome.Success(posts)
            )
    }

    @Test
    fun `When we get a post detail then should return it from local data source`() {
        // Given
        val postId = 1L

        val postWithUserAndComments = PostWithUserAndComments(
            PostEntity(
                postId,
                1L,
                "a sample post"
            ),
            UserEntity(
                1L,
                "name",
                "username",
                "https://avatar.com"
            ),
            listOf(
                CommentEntity(
                    1L,
                    postId,
                    "name",
                    "email",
                    "this is a comment"
                )
            )
        )

        val expected = PostDetail(
            postId.toPostId(),
            "a sample post",
            User(
                1L.toUserId(),
                "name",
                "username",
                "https://avatar.com".toAvatar()
            ),
            listOf(
                Comment(
                    1L.toCommentId(),
                    postId.toPostId(),
                    "name",
                    "email",
                    "this is a comment"
                )
            )
        )
        every { postLocalDataSource.getPostDetail(postId.toPostId()) } returns Single.just(postWithUserAndComments)

        // When
        val test = postRepository.getPostDetail(postId.toPostId()).test()

        // Then
        test.assertNoErrors()
            .assertComplete()
            .assertValue(expected)
    }

}