package com.abhay.twitterdemo.utils

import android.content.Context
import com.abhay.twitterdemo.data.db.AppDatabase
import com.abhay.twitterdemo.data.db.DatabaseBuilder
import com.abhay.twitterdemo.data.db.HomeTimelineTweetDao
import com.abhay.twitterdemo.data.db.Repository
import com.abhay.twitterdemo.data.rest.CustomTwitterApiService
import com.abhay.twitterdemo.data.rest.CustomTwitterClient
import com.abhay.twitterdemo.data.rest.TwitterDemoRemoteDataSource
import com.twitter.sdk.android.core.TwitterCore

object InjectorUtils {

    fun getAppDatabase(context: Context): AppDatabase {
        return DatabaseBuilder.getInstance(context);
    }

    fun provideTwitterApiService(): CustomTwitterApiService {
        val session = TwitterCore.getInstance().sessionManager.activeSession;
        return CustomTwitterClient(session).getCustomService()
    }

    fun provideTwitterDemoRemoteDataSource(): TwitterDemoRemoteDataSource {
        return TwitterDemoRemoteDataSource(provideTwitterApiService())
    }

    fun provideRepository(
        homeTimelineTweetDao: HomeTimelineTweetDao,
        twitterDemoRemoteDataSource: TwitterDemoRemoteDataSource
    ): Repository {
        return Repository.getInstance(
            homeTimelineTweetDao,
            twitterDemoRemoteDataSource
        )
    }
}