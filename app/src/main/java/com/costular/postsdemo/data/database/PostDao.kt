package com.costular.postsdemo.data.database

import androidx.room.Dao
import androidx.room.Query
import com.costular.postsdemo.data.model.PostEntity
import com.costular.postsdemo.data.model.PostWithUserAndComments
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
abstract class PostDao : BaseDao<PostEntity>() {

    @Query("SELECT * FROM posts")
    abstract fun observePosts(): Flowable<List<PostEntity>>

    @Query("SELECT * FROM posts where id = :postId")
    abstract fun getPostById(postId: Long): Single<PostWithUserAndComments>

}