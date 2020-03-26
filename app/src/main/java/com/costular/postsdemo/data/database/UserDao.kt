package com.costular.postsdemo.data.database

import androidx.room.Dao
import com.costular.postsdemo.data.model.UserEntity

@Dao
abstract class UserDao : BaseDao<UserEntity>()