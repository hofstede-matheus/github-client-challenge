package com.hofstedematheus.githubclientchallenge.core.services

import com.hofstedematheus.githubclientchallenge.data.model.PublicRepository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubApi {

    @GET("repositories")
    suspend fun getPublicRepositories(@Query("since") since: Int?): Response<List<PublicRepository>>
}