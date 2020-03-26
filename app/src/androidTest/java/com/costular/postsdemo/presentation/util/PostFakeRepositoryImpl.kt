package com.costular.postsdemo.presentation.util

import com.costular.postsdemo.domain.PostRepository
import com.costular.postsdemo.domain.model.*
import io.reactivex.Flowable
import io.reactivex.Single

class PostFakeRepositoryImpl : PostRepository {

    override fun observePosts(): Flowable<Outcome<List<Post>>> =
        Flowable.just(
            Outcome.Success(
                listOf(
                    Post(
                        1L.toPostId(),
                        "title test",
                        "description test"
                    ),
                    Post(
                        2L.toPostId(),
                        "title test 2",
                        "description test 2"
                    )
                )
            )
        )

    override fun getPostDetail(postId: PostId): Single<PostDetail> =
        Single.just(
            PostDetail(
                1L.toPostId(),
                "This is a post",
                "This is the post's description",
                User(
                    1L.toUserId(),
                    "user's name",
                    "username",
                    "https://www.kindpng.com/picc/m/78-786207_user-avatar-png-user-avatar-icon-png-transparent.png".toAvatar()
                ),
                listOf(
                    Comment(
                        1L.toCommentId(),
                        1L.toPostId(),
                        "name",
                        "username",
                        "body"
                    )
                )
            )
        )

}