package com.costular.postsdemo.data.mapper

import com.costular.postsdemo.data.model.CommentDTO
import com.costular.postsdemo.data.model.CommentEntity

class CommentEntityMapper : Mapper<CommentDTO, CommentEntity> {

    override fun map(input: CommentDTO): CommentEntity =
        CommentEntity(
            input.id,
            input.postId,
            input.name,
            input.email,
            input.body
        )

}