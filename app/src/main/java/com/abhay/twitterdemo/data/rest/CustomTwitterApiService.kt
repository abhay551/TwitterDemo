package com.abhay.twitterdemo.data.rest

import com.abhay.twitterdemo.data.model.HomeTimelineTweetModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CustomTwitterApiService {

    @GET("/1.1/statuses/home_timeline.json")
    suspend fun fetchHomeTimeLineTweet(
        @Query("screen_name") screenName: String
    ): Response<List<HomeTimelineTweetModel>>


}