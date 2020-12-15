package com.hofstedematheus.githubclientchallenge.data.model

import com.google.gson.annotations.SerializedName

data class PublicRepository(

    @SerializedName("name")
    val name: String = "",

    @SerializedName("description")
    val description: String = "",

    @SerializedName("avatar_url")
    val avatar_url: String = ""
)