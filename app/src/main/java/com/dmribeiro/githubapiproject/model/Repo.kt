package com.dmribeiro.githubapiproject.model


import com.google.gson.annotations.SerializedName

data class Repo(

    @SerializedName("forks")
    val forks: Int,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,

)