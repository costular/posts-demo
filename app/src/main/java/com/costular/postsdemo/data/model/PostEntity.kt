package com.costular.postsdemo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "user_id") val userId: Long,
    val description: String
)