package com.costular.postsdemo.data.mapper

import com.costular.postsdemo.data.model.PostWithUserAndComments
import com.costular.postsdemo.domain.model.PostDetail
import com.costular.postsdemo.domain.model.toPostId

class PostDetailMapper(
    private val userMapper: UserMapper,
    private val commentMapper: CommentMapper
) : Mapper<PostWithUserAndComments, PostDetail> {

    override fun map(input: PostWithUserAndComments): PostDetail =
        PostDetail(
            input.post.id.toPostId(),
            input.post.title,
            input.post.description,
            userMapper.map(input.user),
            commentMapper.mapList(input.comments)
        )

}