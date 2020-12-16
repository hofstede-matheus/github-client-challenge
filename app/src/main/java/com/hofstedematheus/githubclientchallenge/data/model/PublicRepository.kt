package com.hofstedematheus.githubclientchallenge.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PublicRepository(

    @SerializedName("name")
    val name: String = "",

    @SerializedName("description")
    val description: String = "",

    @SerializedName("owner")
    val owner: Owner,
): Serializable

data class Owner(
    @SerializedName("avatar_url")
    val avatarUrl: String = "",

    @SerializedName("login")
    val userName: String = ""
)
