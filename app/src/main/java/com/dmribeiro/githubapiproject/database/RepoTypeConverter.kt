package com.dmribeiro.githubapiproject.database

import androidx.room.TypeConverter
import com.dmribeiro.githubapiproject.model.Owner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class RepoTypeConverter {

    val gson = Gson()

    @TypeConverter
    fun ownerToString(owner: Owner): String{
        return gson.toJson(owner)
    }

    @TypeConverter
    fun stringToOwner(owner: String): Owner{
        val listType = object : TypeToken<Owner>(){}.type
        return gson.fromJson(owner, listType)
    }
}