package com.costular.postsdemo.presentation.posts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.costular.postsdemo.R
import com.costular.postsdemo.domain.model.Outcome
import com.costular.postsdemo.domain.model.Post
import com.costular.postsdemo.domain.model.PostId
import com.costular.postsdemo.util.MarginItemDecoration
import com.costular.postsdemo.util.extensions.gone
import com.costular.postsdemo.util.extensions.handle
import com.costular.postsdemo.util.extensions.observe
import com.costular.postsdemo.util.extensions.visible
import kotlinx.android.synthetic.main.fragment_posts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsFragment : Fragment(R.layout.fragment_posts) {

    val postsViewModel: PostsViewModel by viewModel()

    private val adapter: PostAdapter = PostAdapter { post ->
        postsViewModel.clickOnPost(post)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        setUpRecycler()
        observeState()
    }

    private fun setUpToolbar() {
        toolbar.title = getString(R.string.post_title)
    }

    private fun setUpRecycler() {
        with (postRecycler) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = this@PostsFragment.adapter
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelOffset(R.dimen.card_horizontal_margin),
                    resources.getDimensionPixelOffset(R.dimen.card_vertical_margin)
                )
            )
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    appbar.isSelected = recyclerView.canScrollVertically(-1)
                }
            })
        }
    }

    private fun observeState() {
        with (postsViewModel) {
            observe(posts) {
                onPostsReceived(it)
            }
            observe (openPostDetailEvent) {
                it.handle { postId -> navigateToPost(postId) }
            }
        }

        postsViewModel.observePosts()
    }

    private fun onPostsReceived(result: Outcome<List<Post>>) {
        when (result) {
            is Outcome.Loading -> showLoading()
            is Outcome.Success -> onPostsReceived(result.posts)
        }
    }

    private fun showLoading() {
        postsLoading.visible()
    }

    private fun hideLoading() {
        postsLoading.gone()
    }

    private fun onPostsReceived(posts: List<Post>) {
        hideLoading()

        if (posts.isNotEmpty()) {
            emptyLayout.gone()
            postRecycler.visible()
            adapter.submitList(posts)
        } else {
            postRecycler.gone()
            emptyLayout.visible()
        }
    }

    private fun navigateToPost(postId: PostId) {
        val action = PostsFragmentDirections.actionPostsFragmentToPostDetailFragment(postId.toLong())
        findNavController().navigate(action)
    }

}