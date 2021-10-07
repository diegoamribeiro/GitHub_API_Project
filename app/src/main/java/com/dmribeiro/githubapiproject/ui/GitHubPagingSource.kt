package com.dmribeiro.githubapiproject.ui

import android.app.DownloadManager
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dmribeiro.githubapiproject.data.remote.RepoApi
import com.dmribeiro.githubapiproject.model.Repo
import retrofit2.HttpException
import java.io.IOException

const val GITHUB_STARTING_PAGE_INDEX = 1

class GitHubPagingSource(
    private val repoApi: RepoApi,
): PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        val position = params.key ?: GITHUB_STARTING_PAGE_INDEX

        return try {
            val response = repoApi.getRepositoriesByLanguage(position, params.loadSize)
            val repos = response.items
            Log.d("***", repos.toString())
            LoadResult.Page(
                data = repos,
                prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (repos.isEmpty()) null else position + 1
            )

        }catch (exception: IOException){
            LoadResult.Error(exception)
        }catch (exception: HttpException){
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}