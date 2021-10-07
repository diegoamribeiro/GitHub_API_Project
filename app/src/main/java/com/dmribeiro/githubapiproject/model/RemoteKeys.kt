package com.dmribeiro.githubapiproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dmribeiro.githubapiproject.utils.Constants.REMOTE_KEYS_TABLE

@Entity(tableName = REMOTE_KEYS_TABLE)
data class RemoteKeys(

    @PrimaryKey
    val repoId: Long,
    val prevKey: Int?,
    val nextKey: Int?
)