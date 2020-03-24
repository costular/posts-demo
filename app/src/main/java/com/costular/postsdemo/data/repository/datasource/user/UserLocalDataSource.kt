package com.costular.postsdemo.data.repository.datasource.user

import com.costular.postsdemo.data.model.UserEntity
import io.reactivex.Completable

interface UserLocalDataSource {

    fun insertUsers(users: List<UserEntity>): Completable

}