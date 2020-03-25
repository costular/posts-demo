package com.costular.postsdemo.data.repository.datasource.user

import com.costular.postsdemo.data.model.UserDTO
import com.costular.postsdemo.data.net.UserApi
import io.reactivex.Single

interface UserRemoteDataSource {

    fun getUsers(): Single<List<UserDTO>>

}

class UserRemoteDataSourceImpl(private val userApi: UserApi): UserRemoteDataSource {

    override fun getUsers(): Single<List<UserDTO>> = userApi.getUsers()

}