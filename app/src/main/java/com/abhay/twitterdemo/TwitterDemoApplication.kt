package com.abhay.twitterdemo

import android.app.Application
import android.util.Log
import com.facebook.drawee.backends.pipeline.Fresco
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig

class TwitterDemoApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this);
        initTwitter()
    }

    private fun initTwitter() {
        val config = TwitterConfig.Builder(this)
            .logger(DefaultLogger(Log.DEBUG))
            .twitterAuthConfig(
                TwitterAuthConfig(
                    getString(R.string.twitter_api_key),
                    getString(R.string.twitter_api_secret)
                )
            )
            .debug(true)
            .build()
        Twitter.initialize(config)
    }

}