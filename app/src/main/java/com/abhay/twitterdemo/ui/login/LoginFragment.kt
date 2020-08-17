package com.abhay.twitterdemo.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.abhay.twitterdemo.MainActivity
import com.abhay.twitterdemo.R
import com.abhay.twitterdemo.data.pref.PrefKeys
import com.abhay.twitterdemo.data.pref.SharedPrefs
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.models.User
import kotlinx.android.synthetic.main.login_fragment.*


class LoginFragment : Fragment() {


    private lateinit var viewModel: LoginViewModel

    private lateinit var mContext: Context


    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        setUpBackButtonListener()
        (mContext as MainActivity).setToolbarTitle(getString(R.string.txt_login))
        navigateToHomeTimeline()
        bt_login_with_twitter.callback = object :
            Callback<TwitterSession>() {

            override fun success(result: Result<TwitterSession>) {
                fetchUserData(result.data)
            }

            override fun failure(exception: TwitterException) {
                // Do something on failure
            }

        }
    }

    private fun navigateToHomeTimeline() {
        val isLogin: Boolean =
            SharedPrefs.get(mContext, PrefKeys.IS_LOGIN, false) as Boolean
        if (isLogin) {
            findNavController().navigate(R.id.loginFragmentToHomeTimelineFragment, null)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        bt_login_with_twitter.onActivityResult(requestCode, resultCode, data)
    }

    private fun fetchUserData(session: TwitterSession) {
        viewModel.fetchUserData(session, object : ILoginCallBack {

            override fun onLoginSuccess(user: User) {
                SharedPrefs.save(context!!, PrefKeys.AUTH_TOKEN, session.authToken.token)
                SharedPrefs.save(context!!, PrefKeys.SCREEN_NAME, user.screenName)
                SharedPrefs.save(context!!, PrefKeys.IS_LOGIN, true)
                navigateToHomeTimeline()
            }

            override fun onLoginFailed(t: Throwable) {

            }

        })
    }

    private fun setUpBackButtonListener() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


}