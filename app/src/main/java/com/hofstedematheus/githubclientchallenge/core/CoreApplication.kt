package com.hofstedematheus.githubclientchallenge.core

import android.app.Application
import com.hofstedematheus.githubclientchallenge.core.koin_modules.publicRepositories
import com.hofstedematheus.githubclientchallenge.core.koin_modules.repositoryPage
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CoreApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CoreApplication)

            modules(
                    listOf(
                            publicRepositories,
                            repositoryPage
                    )
            )
        }
    }
}