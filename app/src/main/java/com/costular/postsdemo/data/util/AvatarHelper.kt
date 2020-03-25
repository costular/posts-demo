package com.costular.postsdemo.data.util

import com.costular.postsdemo.domain.model.UserId

interface AvatarHelper {

    fun getAvatarUrl(userId: UserId): String

}

class AvatarHelperImpl : AvatarHelper {

    override fun getAvatarUrl(userId: UserId): String =
        "http://api.adorable.io/avatars/${userId.asLong()}"

}