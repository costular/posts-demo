package com.costular.postsdemo.di

import com.costular.postsdemo.domain.interactor.GetPostDetail
import com.costular.postsdemo.presentation.postdetail.PostDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val postDetail = module {

    single { GetPostDetail(get()) }

    viewModel { PostDetailViewModel(get(), get()) }

}