package com.costular.postsdemo.domain.model

inline class Avatar(val url: String) {
    fun asString(): String = url
}

fun String.toAvatar(): Avatar = Avatar(this)


inline class UserId(val userId: Long) {
    fun asLong(): Long = userId
}

fun Long.toUserId(): UserId = UserId(this)

data class User(
    val id: UserId,
    val name: String,
    val username: String,
    val avatar: Avatar
)