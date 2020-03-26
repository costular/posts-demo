package com.costular.postsdemo.presentation.posts

fun posts(function: PostsRobot.() -> Unit) = PostsRobot().apply { function() }

class PostsRobot {

    val postScreen = PostsScreen()

    fun scrollToPosition(position: Int) {
        postScreen.recycler.scrollTo(position)
    }

    fun clickAtPosition(position: Int) {
        postScreen.recycler.childAt<PostItem>(position) {
            click()
        }
    }

    fun check(function: Result.() -> Unit): Result = Result().apply { function() }

    inner class Result {
        fun isLoadingDisplayed() {
            postScreen.postsLoading.isDisplayed()
        }

        fun hasSize(count: Int) =
            postScreen.recycler.hasSize(count)

        fun checkPositionHasTitle(position: Int, title: String) =
            postScreen.recycler.childAt<PostItem>(position - 1) {
                postTitle.hasText(title)
            }

        fun checkPositionHasDescription(position: Int, description: String) =
            postScreen.recycler.childAt<PostItem>(position - 1) {
                postDescription.hasText(description)
            }

    }

}
