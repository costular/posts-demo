package com.costular.postsdemo.di

import com.costular.postsdemo.data.database.MainDatabase
import com.costular.postsdemo.data.mapper.UserEntityMapper
import com.costular.postsdemo.data.mapper.UserMapper
import com.costular.postsdemo.data.net.UserApi
import com.costular.postsdemo.data.repository.datasource.user.UserLocalDataSource
import com.costular.postsdemo.data.repository.datasource.user.UserLocalDataSourceImpl
import com.costular.postsdemo.data.util.AvatarHelper
import com.costular.postsdemo.data.util.AvatarHelperImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val users = module {

    single<AvatarHelper> { AvatarHelperImpl() }

    single { UserEntityMapper(get()) }

    single { UserMapper() }

    single { get<MainDatabase>().userDao() }

    single<UserLocalDataSource> { UserLocalDataSourceImpl(get()) }

    single<UserApi> { get<Retrofit>().create() }

}