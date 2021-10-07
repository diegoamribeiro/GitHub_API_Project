package com.dmribeiro.githubapiproject.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dmribeiro.githubapiproject.model.Repo

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRepos(repo: List<Repo>)

    @Query("SELECT * FROM REPOS_TABLE ORDER BY stargazersCount DESC, name ASC")
    fun getAllRepos(): PagingSource<Int, Repo>

    @Query("DELETE FROM REPOS_TABLE")
    suspend fun clearRepos()
}