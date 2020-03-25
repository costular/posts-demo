package com.costular.postsdemo.domain.model


inline class PostId(val postId: Long) {

    fun toLong(): Long = postId

}

fun Long.toPostId(): PostId = PostId(this)


data class Post(
    val id: PostId,
    val description: String
)