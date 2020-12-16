package com.hofstedematheus.githubclientchallenge.data.datasources

import com.hofstedematheus.githubclientchallenge.core.services.GithubApiService
import com.hofstedematheus.githubclientchallenge.data.core.Result
import com.hofstedematheus.githubclientchallenge.data.constants.ERROR_GENERAL
import com.hofstedematheus.githubclientchallenge.data.constants.ERROR_INVALID_USER_NAME
import com.hofstedematheus.githubclientchallenge.data.model.PublicRepository
import com.hofstedematheus.githubclientchallenge.data.repository.RepositoriesRepository

class ApiRepositoriesDataSource: RepositoriesRepository {
    private val api = GithubApiService()


    override suspend fun getPublicRepositories(since: Int?): Result<List<PublicRepository>> {

        with(api.getPublicRepositories(since)) {
            return this.body()?.let {
                return Result.Success(it)
            } ?: Result.Error(ERROR_GENERAL)

        }

    }

    override suspend fun searchPublicRepositoryByName(name: String): Result<PublicRepository> {
        val params = name.split('/')
        if (params.size != 2) return Result.Error(ERROR_INVALID_USER_NAME)
        with(api.searchPublicRepositoryByName(
            owner = params[0],
            repo = params[1]
        )) {
            return this.body()?.let {
                return Result.Success(it)
            } ?: Result.Error(ERROR_GENERAL)

        }
    }
}