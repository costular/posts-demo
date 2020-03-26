package com.costular.postsdemo.presentation.util

import com.costular.postsdemo.domain.PostRepository
import com.costular.postsdemo.domain.interactor.ObservePosts
import com.costular.postsdemo.presentation.posts.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mockedModule = module(override = true) {

    single<PostRepository> { PostFakeRepositoryImpl() }

    single { ObservePosts(get()) }

    viewModel {
        PostsViewModel(
            get(),
            get()
        )
    }

}