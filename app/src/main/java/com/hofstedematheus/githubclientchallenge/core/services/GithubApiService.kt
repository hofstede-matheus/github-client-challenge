package com.hofstedematheus.githubclientchallenge.core.services

import com.hofstedematheus.githubclientchallenge.BuildConfig
import com.hofstedematheus.githubclientchallenge.data.model.PublicRepository
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path

class GithubApiService {
    private val api: GithubApi = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GithubApi::class.java)

    suspend fun getPublicRepositories(since: Int?): Response<List<PublicRepository>> = api.getPublicRepositories(since)
    suspend fun searchPublicRepositoryByName(owner: String, repo: String): Response<PublicRepository> = api.searchPublicRepositoryByName(owner, repo)
}