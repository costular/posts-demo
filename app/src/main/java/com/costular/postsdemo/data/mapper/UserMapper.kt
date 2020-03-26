package com.costular.postsdemo.data.mapper

import com.costular.postsdemo.data.model.UserEntity
import com.costular.postsdemo.domain.model.User
import com.costular.postsdemo.domain.model.toAvatar
import com.costular.postsdemo.domain.model.toUserId

class UserMapper : Mapper<UserEntity, User> {

    override fun map(input: UserEntity): User =
        User(
            input.id.toUserId(),
            input.name,
            input.username,
            input.avatar.toAvatar()
        )

}