package com.costular.postsdemo.presentation.posts

import com.costular.postsdemo.data.repository.PostRepositoryImpl
import com.costular.postsdemo.domain.PostRepository
import com.costular.postsdemo.domain.interactor.ObservePosts
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mockedModule = module(override = true) {

    single<PostRepository> { PostFakeRepositoryImpl() }

    single { ObservePosts(get()) }

    viewModel { PostsViewModel(get(), get()) }

}