package com.dmribeiro.githubapiproject.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dmribeiro.githubapiproject.data.remote.RepoApi
import com.dmribeiro.githubapiproject.database.RepoDatabase
import com.dmribeiro.githubapiproject.database.RepoRemoteMediator
import com.dmribeiro.githubapiproject.model.Repo
import com.dmribeiro.githubapiproject.utils.Constants.NETWORK_PAGE_SIZE
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ActivityRetainedScoped
class Repository @Inject constructor(
    private val repoApi: RepoApi,
    private val database: RepoDatabase
) {

    fun getSearchResultStream(): Flow<PagingData<Repo>> {
        val pagingSourceFactory = { database.reposDao().getAllRepos() }
        @OptIn(ExperimentalPagingApi::class)
        return Pager(config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = RepoRemoteMediator(repoApi, database),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}