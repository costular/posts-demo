package com.costular.postsdemo.presentation

import android.app.Application
import com.costular.postsdemo.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(listOf(
                app,
                comments,
                users,
                posts,
                postDetail
            ))
        }
    }

}