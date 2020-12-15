package com.hofstedematheus.githubclientchallenge.core.services

import com.hofstedematheus.githubclientchallenge.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubApiService {
    private val api: GithubApi = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GithubApi::class.java)
}