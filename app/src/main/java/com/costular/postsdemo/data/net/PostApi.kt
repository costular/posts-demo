package com.costular.postsdemo.data.net

import com.costular.postsdemo.data.model.PostDTO
import io.reactivex.Single
import retrofit2.http.GET

interface PostApi {

    @GET("posts")
    fun getPosts(): Single<List<PostDTO>>

}