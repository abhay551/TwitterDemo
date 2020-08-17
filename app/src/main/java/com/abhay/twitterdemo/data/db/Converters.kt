package com.abhay.twitterdemo.data.db

import androidx.room.TypeConverter
import com.abhay.twitterdemo.data.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    val gson = Gson()

    @TypeConverter
    fun StringToUser(string: String): User {
        val type = object : TypeToken<User>() {}.type
        return gson.fromJson(string, type)
    }

    @TypeConverter
    fun UserToString(user: User): String {
        return gson.toJson(user)
    }
}