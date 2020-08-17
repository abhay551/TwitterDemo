package com.abhay.twitterdemo.ui.hometimeline

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abhay.twitterdemo.MainActivity
import com.abhay.twitterdemo.R
import com.abhay.twitterdemo.data.db.AppDatabase
import com.abhay.twitterdemo.data.pref.PrefKeys
import com.abhay.twitterdemo.data.pref.SharedPrefs
import com.abhay.twitterdemo.data.rest.TwitterDemoRemoteDataSource
import com.abhay.twitterdemo.utils.BaseResult
import com.abhay.twitterdemo.utils.InjectorUtils
import kotlinx.android.synthetic.main.home_timeline_fragment.*

class HomeTimelineFragment : Fragment() {

    private lateinit var viewModel: HomeTimelineViewModel
    private lateinit var homeTimeLineAdapter: HomeTimeLineAdapter
    private lateinit var mContext: Context


    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.home_timeline_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpViewModel()
        setUpBackButtonListener()
        setUpRecylerViewAdapter()
        observeHomeTimelineTweet()
    }

    private fun setUpViewModel() {
        val appDatabase: AppDatabase = InjectorUtils.getAppDatabase(mContext.applicationContext)
        val twitterDemoRemoteDataSource: TwitterDemoRemoteDataSource =
            InjectorUtils.provideTwitterDemoRemoteDataSource()
        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                InjectorUtils.provideRepository(
                    appDatabase.getHomeTimelineTweetDao(),
                    twitterDemoRemoteDataSource
                )
            )
        ).get(HomeTimelineViewModel::class.java)
    }

    private fun setUpRecylerViewAdapter() {
        homeTimeLineAdapter = HomeTimeLineAdapter(ArrayList(), mContext)
        rvHomeTimeline.adapter = homeTimeLineAdapter
    }

    private fun observeHomeTimelineTweet() {
        val screenName: String =
            SharedPrefs.get(mContext, PrefKeys.SCREEN_NAME, "") as String
        viewModel.getAllHomeTimelineTweet(screenName).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                BaseResult.Status.SUCCESS -> {
                    it.data?.let {
                        rvHomeTimeline.visibility = View.VISIBLE
                        pbProgress.visibility = View.GONE
                        homeTimeLineAdapter.setItems(it)
                    }
                }
                BaseResult.Status.LOADING -> {
                    rvHomeTimeline.visibility = View.GONE
                    pbProgress.visibility = View.VISIBLE
                }
                BaseResult.Status.ERROR
                -> {
                    pbProgress.visibility = View.GONE
                }
            }
        })
        (context as MainActivity).setToolbarTitle("@" + screenName)
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