package com.costular.postsdemo.presentation.posts

import android.view.View
import com.agoda.kakao.progress.KProgressBar
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.costular.postsdemo.R
import org.hamcrest.Matcher

class PostsScreen : Screen<PostsScreen>() {
    val postsLoading = KProgressBar { withId(R.id.postsLoading) }
    val recycler: KRecyclerView = KRecyclerView({
        withId(R.id.postRecycler)
    }, itemTypeBuilder = {
        itemType(::PostItem)
    })
}

class PostItem(parent: Matcher<View>) : KRecyclerItem<PostItem>(parent) {
    val postTitle: KTextView = KTextView(parent) { withId(R.id.postDetailTitleText) }
    val postDescription: KTextView = KTextView(parent) { withId(R.id.postDescriptionText) }
}