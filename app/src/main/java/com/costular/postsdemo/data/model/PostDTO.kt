package com.costular.postsdemo.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostDTO(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String
)