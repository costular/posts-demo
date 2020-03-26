package com.costular.postsdemo.presentation.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.costular.postsdemo.R
import com.costular.postsdemo.domain.model.Post

typealias ClickListener = (Post) -> Unit
class PostAdapter(
    private val clickListener: ClickListener
) : ListAdapter<Post, PostViewHolder>(PostDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}