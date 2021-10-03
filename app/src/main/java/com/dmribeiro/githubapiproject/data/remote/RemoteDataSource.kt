package com.dmribeiro.githubapiproject.data.remote

import com.dmribeiro.githubapiproject.model.Repos
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val repoApi: RepoApi
){

    suspend fun getRepositoriesByLanguage(): Response<Repos>{
        return repoApi.getRepositoriesByLanguage()
    }

}