package com.costular.postsdemo.data.database

import androidx.room.Dao
import androidx.room.Query
import com.costular.postsdemo.data.model.CommentEntity
import io.reactivex.Single

@Dao
abstract class CommentDao : BaseDao<CommentEntity>()