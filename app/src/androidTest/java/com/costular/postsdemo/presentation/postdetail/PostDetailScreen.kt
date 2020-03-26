package com.costular.postsdemo.presentation.postdetail

import com.agoda.kakao.image.KImageView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.costular.postsdemo.R

class PostDetailScreen : Screen<PostDetailScreen>() {

    val title = KTextView { withId(R.id.postDetailTitleText) }
    val body = KTextView { withId(R.id.postDetailBodyText) }
    val commentCount = KTextView { withId(R.id.commentsCountText) }
    val authorImage = KImageView { withId(R.id.authorAvatarImage) }
    val authorName = KTextView { withId(R.id.authorNameText) }

}