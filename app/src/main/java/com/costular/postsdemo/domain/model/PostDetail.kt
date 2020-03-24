package com.costular.postsdemo.domain.model

data class PostDetail(
    val id: PostId,
    val description: String,
    val user: User,
    val comments: List<Comment>
)