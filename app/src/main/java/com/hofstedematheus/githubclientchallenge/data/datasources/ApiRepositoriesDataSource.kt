package com.hofstedematheus.githubclientchallenge.data.datasources

import com.hofstedematheus.githubclientchallenge.core.services.GithubApiService
import com.hofstedematheus.githubclientchallenge.data.core.Result
import com.hofstedematheus.githubclientchallenge.data.model.PublicRepository
import com.hofstedematheus.githubclientchallenge.data.repository.RepositoriesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ApiRepositoriesDataSource: RepositoriesRepository {
    private val api = GithubApiService()


    override suspend fun getPublicRepositories(since: Int?): Result<List<PublicRepository>> {

        with(api.getPublicRepositories(since)) {
            return this.body()?.let {
                return Result.Success(it)
            } ?: Result.Error(this.errorBody().toString())

        }

    }
}