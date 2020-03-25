package com.costular.postsdemo.data.net

import com.costular.postsdemo.data.model.UserDTO
import io.reactivex.Single
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    fun getUsers(): Single<List<UserDTO>>

}