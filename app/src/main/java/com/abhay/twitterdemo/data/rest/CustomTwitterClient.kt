package com.abhay.twitterdemo.data.rest

import com.twitter.sdk.android.core.TwitterApiClient
import com.twitter.sdk.android.core.TwitterSession


class CustomTwitterClient(session: TwitterSession) : TwitterApiClient(session) {

    fun getCustomService(): CustomTwitterApiService {
        return getService(CustomTwitterApiService::class.java)
    }

}