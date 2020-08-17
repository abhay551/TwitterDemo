package com.abhay.twitterdemo.ui.login

import androidx.lifecycle.ViewModel
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.models.User
import retrofit2.Call
import retrofit2.Response

class LoginViewModel : ViewModel() {

    fun fetchUserData(session: TwitterSession, loginCallBack: ILoginCallBack) {
        TwitterCore.getInstance().getApiClient(session).accountService
            .verifyCredentials(true, false, true)
            .enqueue(object : retrofit2.Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        val user = response.body()
                        loginCallBack.onLoginSuccess(user!!)
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    loginCallBack.onLoginFailed(t)
                }
            })
    }
}