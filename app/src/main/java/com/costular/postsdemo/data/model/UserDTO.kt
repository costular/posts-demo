package com.costular.postsdemo.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDTO(
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String
)