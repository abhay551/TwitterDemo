package com.abhay.twitterdemo.data.rest

class TwitterDemoRemoteDataSource constructor(private val service: CustomTwitterApiService) :
    BaseRemoteDataSource() {

    suspend fun fetchHomeTimelineTweet(screenName: String) =
        getResult { service.fetchHomeTimeLineTweet(screenName) }

}