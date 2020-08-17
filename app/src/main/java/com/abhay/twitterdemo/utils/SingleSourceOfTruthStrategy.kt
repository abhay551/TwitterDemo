package com.abhay.twitterdemo.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

fun <T, A> resultLiveData(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> BaseResult<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<BaseResult<T>> =
    liveData(Dispatchers.IO) {
        emit(BaseResult.loading<T>())
        val source = databaseQuery.invoke().map {
            BaseResult.success(
                it
            )
        }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == BaseResult.Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)
        } else if (responseStatus.status == BaseResult.Status.ERROR) {
            emit(
                BaseResult.error<T>(
                    responseStatus.message!!
                )
            )
            emitSource(source)
        }
    }