package com.costular.postsdemo.presentation.util

import androidx.room.Room
import com.costular.postsdemo.BuildConfig
import com.costular.postsdemo.data.database.MainDatabase
import com.costular.postsdemo.util.network.SchedulerProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

val appFake = module(override = true) {

    single<SchedulerProvider> { UiSchedulerProvider() }

    single {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BuildConfig.API_BASE)
            .build()
    }

    single {
        Room.inMemoryDatabaseBuilder(androidContext(), MainDatabase::class.java)
            .build()
    }

}