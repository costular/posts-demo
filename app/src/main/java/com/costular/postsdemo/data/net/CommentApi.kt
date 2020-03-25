package com.costular.postsdemo.data.net

import com.costular.postsdemo.data.model.CommentDTO
import io.reactivex.Single
import retrofit2.http.GET

interface CommentApi {

    @GET("comments")
    fun getComments(): Single<List<CommentDTO>>
    
}