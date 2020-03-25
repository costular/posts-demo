package com.costular.postsdemo.data.orchestrator

import com.costular.postsdemo.data.mapper.*
import com.costular.postsdemo.data.model.CommentDTO
import com.costular.postsdemo.data.model.PostDTO
import com.costular.postsdemo.data.model.PostEntity
import com.costular.postsdemo.data.model.UserDTO
import com.costular.postsdemo.data.repository.datasource.PostOrchestrator
import com.costular.postsdemo.data.repository.datasource.comment.CommentLocalDataSource
import com.costular.postsdemo.data.repository.datasource.comment.CommentRemoteDataSource
import com.costular.postsdemo.data.repository.datasource.post.PostLocalDataSource
import com.costular.postsdemo.data.repository.datasource.post.PostRemoteDataSource
import com.costular.postsdemo.data.repository.datasource.user.UserLocalDataSource
import com.costular.postsdemo.data.repository.datasource.user.UserRemoteDataSource
import com.costular.postsdemo.data.util.AvatarHelper
import com.costular.postsdemo.data.util.AvatarHelperImpl
import com.costular.postsdemo.domain.model.Outcome
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.processors.PublishProcessor
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class PostOrchestratorTest {

    private val postMapper = PostMapper()
    private val postEntityMapper = PostEntityMapper()
    private val postRemote: PostRemoteDataSource = mockk(relaxed = true)
    private val postLocal: PostLocalDataSource = mockk(relaxed = true)

    private val userRemote: UserRemoteDataSource = mockk(relaxed = true)
    private val userLocal: UserLocalDataSource = mockk(relaxed = true)
    private val avatarHelper: AvatarHelper = AvatarHelperImpl()
    private val userEntityMapper = UserEntityMapper(avatarHelper)

    private val commentLocal: CommentLocalDataSource = mockk(relaxed = true)
    private val commentRemote: CommentRemoteDataSource = mockk(relaxed = true)
    private val commentEntityMapper = CommentEntityMapper()

    lateinit var postOrchestrator: PostOrchestrator

    @Before
    fun setUp() {
        postOrchestrator = PostOrchestrator(
            postRemote,
            postLocal,
            postMapper,
            postEntityMapper,
            userRemote,
            userLocal,
            userEntityMapper,
            commentRemote,
            commentLocal,
            commentEntityMapper
        )
    }

    @Test
    fun `Given fetch post remote succeed when loading posts then should insert posts into database`() {
        // Given
        val remotePosts = listOf(PostDTO(1L, 1L, "whatever", "test"))
        val expected = postEntityMapper.mapList(remotePosts)

        every { userRemote.getUsers() } returns Single.just(emptyList())
        every { commentRemote.getComments() } returns Single.just(emptyList())
        every { postLocal.observePosts() } returns Flowable.just(emptyList())
        every { postRemote.getPosts() } returns Single.just(remotePosts)

        // When
        val test = postOrchestrator.fetch().test()

        // Then
        test.assertNotComplete()
            .assertNoErrors()
            .assertValues(
                Outcome.Loading,
                Outcome.Success(emptyList())
            )

        verify { postLocal.insertPosts(expected) }
    }

    @Test
    fun `Given local and delayed remote succeed when loading posts then should return success`() {
        // Given
        val remotePosts = listOf(PostDTO(10L, 11L, "", "remote test"))
        val localPosts = listOf(PostEntity(11L, 10L, "local test"))
        val slot = slot<List<PostEntity>>()

        val local = PublishProcessor.create<List<PostEntity>>()
        val remote = PublishSubject.create<List<PostDTO>>()
        val persister: (List<PostEntity>) -> Completable = {
            local.onNext(it)
            Completable.complete()
        }

        every { userRemote.getUsers() } returns Single.just(emptyList())
        every { commentRemote.getComments() } returns Single.just(emptyList())
        every { postLocal.observePosts() } returns local
        every { postRemote.getPosts() } returns remote.firstOrError()
        every { postOrchestrator.onSaveLocally(capture(slot)) } answers { persister(slot.captured) }

        // When
        val test = postOrchestrator.fetch().test()

        // Send local
        local.onNext(localPosts)

        // Send remote delayed
        Single.just(remotePosts)
            .delay(1, TimeUnit.SECONDS)
            .doOnSuccess { remote.onNext(it) }
            .subscribe()

        // Then
        test
            .awaitCount(3)
            .assertNoErrors()
            .assertNotComplete()
            .assertValues(
                Outcome.Loading,
                Outcome.Success(
                    postMapper.mapList(localPosts)
                ),
                Outcome.Success(
                    postMapper.mapList(
                        postEntityMapper.mapList(remotePosts)
                    )
                )
            )
    }

    @Test
    fun `When loading posts then should fetch users and comments and save them into the database afterwards`() {
        // Given
        val remotePosts = listOf(PostDTO(1L, 1L, "whatever", "test"))
        val expected = postEntityMapper.mapList(remotePosts)

        val remoteUsers = listOf(
            UserDTO(
                1L,
                "user",
                "user",
                "user@gmail.com",
                "",
                ""
            )
        )
        val expectedUsers = userEntityMapper.mapList(remoteUsers)

        val remoteComments = listOf(
            CommentDTO(
                1L,
                1L,
                "whatever",
                "",
                "test"
            )
        )
        val expectedComments = commentEntityMapper.mapList(remoteComments)

        every { postLocal.observePosts() } returns Flowable.just(emptyList())
        every { postRemote.getPosts() } returns Single.just(remotePosts)
        every { userRemote.getUsers() } returns Single.just(remoteUsers)
        every { commentRemote.getComments() } returns Single.just(remoteComments)

        // When
        val test = postOrchestrator.fetch().test()

        // Then
        test.assertNotComplete()
            .assertNoErrors()
            .assertValues(
                Outcome.Loading,
                Outcome.Success(emptyList())
            )

        verify { postLocal.insertPosts(expected) }
        verify { userLocal.insertUsers(expectedUsers) }
        verify { commentLocal.insertComments(expectedComments) }
    }

}