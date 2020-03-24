package com.costular.postsdemo.data.mapper

import com.costular.postsdemo.data.model.UserDTO
import com.costular.postsdemo.data.model.UserEntity
import com.costular.postsdemo.data.util.AvatarHelper
import com.costular.postsdemo.domain.model.toUserId

class UserEntityMapper(
    private val avatarHelper: AvatarHelper
) : Mapper<UserDTO, UserEntity> {

    override fun map(input: UserDTO): UserEntity =
        UserEntity(
            input.id,
            input.name,
            input.username,
            avatarHelper.getAvatarUrl(input.id.toUserId())
        )

}