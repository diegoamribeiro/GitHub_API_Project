package com.dmribeiro.githubapiproject.data.repository

import com.dmribeiro.githubapiproject.data.remote.RemoteDataSource
import com.dmribeiro.githubapiproject.di.NetworkModule
import com.dmribeiro.githubapiproject.model.Repos
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource,
) {

    private val api = NetworkModule.getService()
    suspend fun getAllReposTest(): Response<Repos> {
        return api.getRepositoriesByLanguage()
    }


    val remote = remoteDataSource

}