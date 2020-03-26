package com.costular.postsdemo.presentation.postdetail


fun postDetail(function: PostDetailRobot.() -> Unit) = PostDetailRobot().apply { function() }

class PostDetailRobot {

    val postDetail = PostDetailScreen()

    fun check(function: PostDetailRobot.Result.() -> Unit): PostDetailRobot.Result =
        Result().apply { function() }

    inner class Result {

        fun isTitle(title: String) = postDetail.title { hasText(title) }

        fun isBody(body: String) = postDetail.body { hasText(body) }

        fun isCommentCount(comments: Int) = postDetail.commentCount { hasText(comments.toString()) }

        fun isAuthorName(authorName: String) = postDetail.authorName { hasText(authorName) }

    }

}