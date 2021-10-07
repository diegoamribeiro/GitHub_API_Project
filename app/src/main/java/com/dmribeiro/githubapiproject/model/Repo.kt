package com.dmribeiro.githubapiproject.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dmribeiro.githubapiproject.utils.Constants.REPOS_TABLE
import com.google.gson.annotations.SerializedName

@Entity(tableName = REPOS_TABLE)
data class Repo(

    @PrimaryKey
    @SerializedName("id")
    val id: Long,
    @SerializedName("forks")
    val forks: Int,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
)