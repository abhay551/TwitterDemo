package com.abhay.twitterdemo.ui.hometimeline

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhay.twitterdemo.R
import com.abhay.twitterdemo.data.model.HomeTimelineTweetModel

class HomeTimeLineAdapter(
    private val homeTimelineTweetModels: MutableList<HomeTimelineTweetModel>,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(context)
            .inflate(R.layout.row_tweets, parent, false)
        return HomeTimeLineViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val homeTimeLineTweetModel = homeTimelineTweetModels[position]
        (holder as HomeTimeLineViewHolder).bindItem(homeTimeLineTweetModel)
    }

    override fun getItemCount(): Int {
        return homeTimelineTweetModels.size
    }

    internal fun addItem(homeTimelineTweetModel: HomeTimelineTweetModel) {
        homeTimelineTweetModels.add(homeTimelineTweetModel)
        notifyDataSetChanged()
    }

    internal fun setItems(temHomeTimelineTweetModels: List<HomeTimelineTweetModel>) {
        homeTimelineTweetModels.clear()
        homeTimelineTweetModels.addAll(temHomeTimelineTweetModels)
        notifyDataSetChanged()
    }


}
