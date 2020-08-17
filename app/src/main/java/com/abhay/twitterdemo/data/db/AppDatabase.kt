package com.abhay.twitterdemo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abhay.twitterdemo.data.model.HomeTimelineTweetModel

@Database(entities = [HomeTimelineTweetModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getHomeTimelineTweetDao(): HomeTimelineTweetDao
}