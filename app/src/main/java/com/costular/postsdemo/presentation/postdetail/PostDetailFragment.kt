package com.costular.postsdemo.presentation.postdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.costular.postsdemo.R
import com.costular.postsdemo.domain.model.PostDetail
import com.costular.postsdemo.domain.model.toPostId
import com.costular.postsdemo.util.extensions.handle
import com.costular.postsdemo.util.extensions.observe
import kotlinx.android.synthetic.main.fragment_post_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostDetailFragment : Fragment(R.layout.fragment_post_detail) {

    val postDetailViewModel: PostDetailViewModel by viewModel()

    private val args: PostDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        observeState()
        postDetailViewModel.loadPost(args.id.toPostId())
    }

    private fun setUpToolbar() {
        with (toolbar) {
            title = ""
            setNavigationIcon(R.drawable.ic_close)
            setNavigationOnClickListener { postDetailViewModel.close() }
        }
    }

    private fun observeState() {
        with (postDetailViewModel) {
            observe(post) { post ->
                onPostLoaded(post)
            }
            observe (closeEvent) {
                it.handle { onClose() }
            }
        }
    }

    private fun onPostLoaded(postDetail: PostDetail) {
        Glide.with(authorAvatarImage)
            .load(postDetail.user.avatar.asString())
            .fitCenter()
            .circleCrop()
            .placeholder(R.drawable.bg_placeholder)
            .into(authorAvatarImage)

        authorNameText.text = postDetail.user.name
        commentsCountText.text = postDetail.comments.size.toString()
        postDetailTitleText.text = postDetail.title
        postDetailBodyText.text = postDetail.description
    }

    private fun onClose() {
        findNavController().popBackStack()
    }

}