package com.abhay.twitterdemo.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.abhay.twitterdemo.data.model.HomeTimelineTweetModel

@Dao
interface HomeTimelineTweetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(homeTimelineTweetModel: HomeTimelineTweetModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(homeTimelineTweetModelList: List<HomeTimelineTweetModel>)

    @Query("SELECT * from hometimeline")
    fun getAllHomeTimelineTweet(): LiveData<List<HomeTimelineTweetModel>>

    @Delete
    suspend fun deleteHomeTimelineTweet(user: HomeTimelineTweetModel)

    @Query("DELETE FROM hometimeline")
    suspend fun deleteAll()
}