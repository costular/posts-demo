package com.costular.postsdemo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.costular.postsdemo.data.model.CommentEntity
import com.costular.postsdemo.data.model.PostEntity
import com.costular.postsdemo.data.model.UserEntity

@Database(entities = [PostEntity::class, UserEntity::class, CommentEntity::class], version = 1)
abstract class MainDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

    abstract fun userDao(): UserDao

    abstract fun commentDao(): CommentDao

}