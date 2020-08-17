package com.abhay.twitterdemo.ui.hometimeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abhay.twitterdemo.data.db.Repository

class ViewModelFactory(private val repository: Repository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeTimelineViewModel::class.java)) {
            return HomeTimelineViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}