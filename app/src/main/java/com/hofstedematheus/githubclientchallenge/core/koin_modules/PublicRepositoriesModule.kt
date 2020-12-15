package com.hofstedematheus.githubclientchallenge.core.koin_modules

import com.hofstedematheus.githubclientchallenge.data.datasources.ApiRepositoriesDataSource
import com.hofstedematheus.githubclientchallenge.data.repository.RepositoriesRepository
import com.hofstedematheus.githubclientchallenge.ui.scenes.publicrepositories.PublicRepositoriesViewModel
import org.koin.android.viewmodel.compat.ViewModelCompat.viewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val publicRepositories = module {

    single<RepositoriesRepository> { ApiRepositoriesDataSource() }

    viewModel {
        PublicRepositoriesViewModel(
            repository = get()
        )
    }
}