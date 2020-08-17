package com.abhay.twitterdemo.ui.hometimeline

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.abhay.twitterdemo.data.db.Repository
import com.abhay.twitterdemo.data.model.HomeTimelineTweetModel
import com.abhay.twitterdemo.utils.BaseResult

class HomeTimelineViewModel(private var repository: Repository) : ViewModel() {

    fun getAllHomeTimelineTweet(screenName: String): LiveData<BaseResult<List<HomeTimelineTweetModel>>> {
        return repository.fetchDataFromDbOrNetwork(screenName);
    }
}
