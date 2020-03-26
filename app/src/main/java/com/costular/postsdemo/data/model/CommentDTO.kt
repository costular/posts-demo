package com.costular.postsdemo.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentDTO(
    val id: Long,
    val postId: Long,
    val name: String,
    val email: String,
    val body: String
)