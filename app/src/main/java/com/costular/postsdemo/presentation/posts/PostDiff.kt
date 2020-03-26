package com.costular.postsdemo.presentation.posts

import androidx.recyclerview.widget.DiffUtil
import com.costular.postsdemo.domain.model.Post

object PostDiff : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem == newItem

}