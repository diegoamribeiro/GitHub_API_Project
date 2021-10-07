package com.dmribeiro.githubapiproject.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.dmribeiro.githubapiproject.data.remote.RemoteDataSource
import com.dmribeiro.githubapiproject.data.remote.RepoApi
import com.dmribeiro.githubapiproject.model.Repo
import com.dmribeiro.githubapiproject.ui.GitHubPagingSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val repoApi: RepoApi,
) {

    fun getSearchResultStream() =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false),
            pagingSourceFactory = { GitHubPagingSource(repoApi)}
        ).flow


    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }

}