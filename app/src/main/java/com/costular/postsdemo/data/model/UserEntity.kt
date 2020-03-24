package com.costular.postsdemo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val username: String,
    val avatar: String
)