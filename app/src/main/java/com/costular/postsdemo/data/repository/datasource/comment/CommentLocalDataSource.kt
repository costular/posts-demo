package com.costular.postsdemo.data.repository.datasource.comment

import com.costular.postsdemo.data.database.CommentDao
import com.costular.postsdemo.data.model.CommentEntity

interface CommentLocalDataSource {

    fun insertComments(comments: List<CommentEntity>)

}

class CommentLocalDataSourceImpl(private val commentDao: CommentDao) : CommentLocalDataSource {

    override fun insertComments(comments: List<CommentEntity>) =
        commentDao.insertOrUpdate(comments)

}