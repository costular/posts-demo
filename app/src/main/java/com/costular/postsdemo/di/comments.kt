package com.costular.postsdemo.di

import com.costular.postsdemo.data.database.MainDatabase
import com.costular.postsdemo.data.mapper.CommentEntityMapper
import com.costular.postsdemo.data.mapper.CommentMapper
import com.costular.postsdemo.data.net.CommentApi
import com.costular.postsdemo.data.repository.datasource.comment.CommentLocalDataSource
import com.costular.postsdemo.data.repository.datasource.comment.CommentLocalDataSourceImpl
import com.costular.postsdemo.data.repository.datasource.comment.CommentRemoteDataSource
import com.costular.postsdemo.data.repository.datasource.comment.CommentRemoteDataSourceImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val comments = module {

    single { CommentMapper() }

    single { CommentEntityMapper() }

    single { get<MainDatabase>().commentDao() }

    single<CommentApi> { get<Retrofit>().create() }

    single<CommentLocalDataSource> { CommentLocalDataSourceImpl(get()) }

    single<CommentRemoteDataSource> { CommentRemoteDataSourceImpl(get()) }

}