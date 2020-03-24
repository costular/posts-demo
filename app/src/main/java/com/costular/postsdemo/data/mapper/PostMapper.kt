package com.costular.postsdemo.data.mapper

import com.costular.postsdemo.data.model.PostEntity
import com.costular.postsdemo.domain.model.Post
import com.costular.postsdemo.domain.model.toPostId

class PostMapper : Mapper<PostEntity, Post> {

    override fun map(input: PostEntity): Post =
        Post(
            input.id.toPostId(),
            input.description
        )

}