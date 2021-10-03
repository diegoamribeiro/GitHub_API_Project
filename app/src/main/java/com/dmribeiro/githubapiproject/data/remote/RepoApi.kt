package com.dmribeiro.githubapiproject.data.remote

import com.dmribeiro.githubapiproject.model.Repos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoApi {

    @GET("repositories")
    suspend fun getRepositoriesByLanguage(
      @Query("q") language: String = "language:kotlin",
      @Query("sort") sort: String = "stars",
      @Query("page") page: Int = 1
    ): Response<Repos>

}