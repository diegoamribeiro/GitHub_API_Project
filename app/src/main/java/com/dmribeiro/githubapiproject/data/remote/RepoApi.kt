package com.dmribeiro.githubapiproject.data.remote

import com.dmribeiro.githubapiproject.model.RepoResponse
import com.dmribeiro.githubapiproject.utils.Constants.GITHUB_TOKEN
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RepoApi {

    //@Headers(GITHUB_TOKEN)
    @GET("repositories?q=language:kotlin&sort=stars")
    suspend fun getRepositoriesByLanguage(
      @Query("page") page: Int,
      @Query("per_page") perPage: Int
    ): RepoResponse

}