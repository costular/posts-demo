package com.costular.postsdemo.data.repository.datasource.user

import com.costular.postsdemo.data.model.UserDTO
import io.reactivex.Single

interface UserRemoteDataSource {

    fun getUsers(): Single<List<UserDTO>>

}