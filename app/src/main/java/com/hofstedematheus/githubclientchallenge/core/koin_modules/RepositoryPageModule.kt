package com.hofstedematheus.githubclientchallenge.core.koin_modules

import com.hofstedematheus.githubclientchallenge.data.datasources.ApiRepositoriesDataSource
import com.hofstedematheus.githubclientchallenge.data.repository.RepositoriesRepository
import com.hofstedematheus.githubclientchallenge.ui.scenes.publicrepositories.PublicRepositoriesViewModel
import com.hofstedematheus.githubclientchallenge.ui.scenes.repositorypage.RepositoryPageViewModel
import org.koin.android.viewmodel.compat.ViewModelCompat.viewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryPage = module {

    viewModel {
        RepositoryPageViewModel()
    }
}