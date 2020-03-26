package com.costular.postsdemo.presentation.posts

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.costular.postsdemo.domain.model.Post
import kotlinx.android.synthetic.main.item_post.view.*

class PostViewHolder(
    view: View,
    private val clickListener: ClickListener
) : RecyclerView.ViewHolder(view) {

    fun bind(post: Post) {
        with (itemView) {
            postDetailTitleText.text = post.title
            postDescriptionText.text = post.description
            setOnClickListener { clickListener.invoke(post) }
        }
    }

}