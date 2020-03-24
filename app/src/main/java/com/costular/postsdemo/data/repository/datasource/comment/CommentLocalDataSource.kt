package com.costular.postsdemo.data.repository.datasource.comment

import com.costular.postsdemo.data.database.CommentDao
import com.costular.postsdemo.data.model.CommentEntity
import io.reactivex.Completable

interface CommentLocalDataSource {

    fun insertComments(comments: List<CommentEntity>): Completable

}

class CommentLocalDataSourceImpl(private val commentDao: CommentDao) : CommentLocalDataSource {

    override fun insertComments(comments: List<CommentEntity>): Completable =
        commentDao.insertOrUpdate(comments)

}