package com.abhay.twitterdemo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.abhay.twitterdemo.data.db.Converters

@Entity(tableName = "hometimeline")
data class HomeTimelineTweetModel(
    val created_at: String,
    val favorite_count: Int,
    val favorited: Boolean,
    @PrimaryKey val id: Long,
    val id_str: String,
    val retweet_count: Int,
    val retweeted: Boolean,
    val source: String,
    val text: String,
    @TypeConverters(Converters::class) val user: User
)