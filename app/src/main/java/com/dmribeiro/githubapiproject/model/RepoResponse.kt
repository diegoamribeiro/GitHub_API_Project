package com.dmribeiro.githubapiproject.model


import com.google.gson.annotations.SerializedName

data class RepoResponse(
    @SerializedName("items")
    val items: List<Repo> = emptyList(),
    @SerializedName("total_count")
    val totalCount: Int,
    val nextPage: Int? = null
)