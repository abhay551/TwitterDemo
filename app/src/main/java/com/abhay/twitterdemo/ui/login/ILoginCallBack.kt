package com.abhay.twitterdemo.ui.login

import com.twitter.sdk.android.core.models.User

interface ILoginCallBack {
    fun onLoginSuccess(user:User)
    fun onLoginFailed( t: Throwable)
}