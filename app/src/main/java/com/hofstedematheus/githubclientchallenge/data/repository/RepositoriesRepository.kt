package com.hofstedematheus.githubclientchallenge.data.repository

import com.hofstedematheus.githubclientchallenge.data.core.Result
import com.hofstedematheus.githubclientchallenge.data.model.PublicRepository

interface RepositoriesRepository {
    suspend fun getPublicRepositories(since: Int?): Result<List<PublicRepository>>
}