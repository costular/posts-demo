package com.costular.postsdemo.di

import com.costular.postsdemo.data.database.MainDatabase
import com.costular.postsdemo.data.mapper.*
import com.costular.postsdemo.data.net.PostApi
import com.costular.postsdemo.data.repository.PostRepositoryImpl
import com.costular.postsdemo.data.repository.datasource.PostOrchestrator
import com.costular.postsdemo.data.repository.datasource.post.PostLocalDataSource
import com.costular.postsdemo.data.repository.datasource.post.PostLocalDataSourceImpl
import com.costular.postsdemo.data.repository.datasource.post.PostRemoteDataSource
import com.costular.postsdemo.data.repository.datasource.post.PostRemoteDataSourceImpl
import com.costular.postsdemo.domain.PostRepository
import com.costular.postsdemo.domain.interactor.ObservePosts
import com.costular.postsdemo.presentation.posts.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val posts = module {

    single { PostMapper() }

    single { PostEntityMapper() }

    single { PostDetailMapper(get(), get()) }

    single { get<MainDatabase>().postDao() }

    single<PostApi> { get<Retrofit>().create() }

    single {
        PostOrchestrator(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single<PostLocalDataSource> { PostLocalDataSourceImpl(get()) }

    single<PostRemoteDataSource> { PostRemoteDataSourceImpl(get()) }

    single<PostRepository> { PostRepositoryImpl(get(), get(), get()) }

    single { ObservePosts(get()) }

    viewModel { PostsViewModel(get(), get()) }

}