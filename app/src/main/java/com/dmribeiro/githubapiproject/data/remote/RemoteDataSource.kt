package com.dmribeiro.githubapiproject.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.dmribeiro.githubapiproject.model.RepoResponse
import com.dmribeiro.githubapiproject.ui.GitHubPagingSource
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val repoApi: RepoApi
){

    fun getRepositoriesByLanguage() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {GitHubPagingSource(repoApi)}
        ).liveData


}