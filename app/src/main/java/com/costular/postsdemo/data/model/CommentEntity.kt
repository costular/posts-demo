package com.costular.postsdemo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "comments",
    indices = [
        Index(value = ["post_id"], unique = true)
    ]
)
data class CommentEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "post_id") val postId: Long,
    val name: String,
    val email: String,
    val body: String
)