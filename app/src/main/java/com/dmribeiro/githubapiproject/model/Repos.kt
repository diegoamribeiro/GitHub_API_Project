package com.dmribeiro.githubapiproject.model


import com.google.gson.annotations.SerializedName

data class Repos(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<Repo>,
    @SerializedName("total_count")
    val totalCount: Int
)