package com.dmribeiro.githubapiproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dmribeiro.githubapiproject.model.RemoteKeys
import com.dmribeiro.githubapiproject.model.Repo

@Database(
    entities = [Repo::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(RepoTypeConverter::class)
abstract class RepoDatabase : RoomDatabase() {

    abstract fun reposDao(): RepoDao
    abstract fun remoteKeysDao(): RemoteKeysDao

}