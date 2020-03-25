package com.costular.postsdemo.domain.model

inline class CommentId(val commentId: Long) {

    fun toLong(): Long = commentId

}

fun Long.toCommentId(): CommentId = CommentId(this)


data class Comment(
    val id: CommentId,
    val postId: PostId,
    val name: String,
    val email: String,
    val body: String
)