package com.abhay.twitterdemo.data.db

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
    const val APP_DB_NAME = "twitter_demo_db";

    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = buildRoomDatabase(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDatabase(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            APP_DB_NAME
        ).build()

}