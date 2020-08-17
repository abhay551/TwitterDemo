package com.abhay.twitterdemo.data.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.abhay.twitterdemo.data.model.HomeTimelineTweetModel
import com.abhay.twitterdemo.data.rest.TwitterDemoRemoteDataSource
import com.abhay.twitterdemo.utils.BaseResult
import com.abhay.twitterdemo.utils.resultLiveData
import kotlinx.coroutines.Dispatchers

class Repository(
    private val homeTimelineTweetDao: HomeTimelineTweetDao,
    private val twitterDemoRemoteDataSource: TwitterDemoRemoteDataSource
) {

    companion object {

        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            homeTimelineTweetDao: HomeTimelineTweetDao,
            twitterDemoRemoteDataSource: TwitterDemoRemoteDataSource
        ) =
            instance ?: synchronized(this) {
                instance ?: Repository(
                    homeTimelineTweetDao,
                    twitterDemoRemoteDataSource
                ).also { instance = it }
            }
    }

    fun fetchDataFromDbOrNetwork(screenName: String): LiveData<BaseResult<List<HomeTimelineTweetModel>>> {
        val data = resultLiveData(
            databaseQuery = { homeTimelineTweetDao.getAllHomeTimelineTweet() },
            networkCall = { twitterDemoRemoteDataSource.fetchHomeTimelineTweet(screenName) },
            saveCallResult = { homeTimelineTweetDao.insertAll(it) })
        return data
    }

}