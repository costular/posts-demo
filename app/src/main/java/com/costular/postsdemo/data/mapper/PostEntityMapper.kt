package com.costular.postsdemo.data.mapper

import com.costular.postsdemo.data.model.PostDTO
import com.costular.postsdemo.data.model.PostEntity

class PostEntityMapper : Mapper<PostDTO, PostEntity> {

    override fun map(input: PostDTO): PostEntity =
        PostEntity(
            input.id,
            input.userId,
            input.body
        )

}