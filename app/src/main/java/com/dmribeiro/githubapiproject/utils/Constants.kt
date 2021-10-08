package com.dmribeiro.githubapiproject.utils

object Constants {

    // Retrofit
    const val BASE_URL = "https://api.github.com/search/"
    const val GITHUB_STARTING_PAGE_INDEX = 1
    const val GITHUB_TOKEN = "Authorization: token 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"
    const val NETWORK_PAGE_SIZE = 15

    // ROOM
    const val DATABASE_NAME = "repos_database"
    const val REPOS_TABLE = "repos_table"
    const val REMOTE_KEYS_TABLE = "remote_keys"
}