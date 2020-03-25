package com.costular.postsdemo.data.mapper

import com.costular.postsdemo.data.model.CommentEntity
import com.costular.postsdemo.domain.model.Comment
import com.costular.postsdemo.domain.model.toCommentId
import com.costular.postsdemo.domain.model.toPostId

class CommentMapper : Mapper<CommentEntity, Comment> {

    override fun map(input: CommentEntity): Comment =
        Comment(
            input.id.toCommentId(),
            input.postId.toPostId(),
            input.name,
            input.email,
            input.body
        )

}