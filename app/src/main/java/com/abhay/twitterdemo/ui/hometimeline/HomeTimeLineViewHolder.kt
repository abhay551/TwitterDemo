package com.abhay.twitterdemo.ui.hometimeline

import android.net.Uri
import android.text.format.DateUtils
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abhay.twitterdemo.R
import com.abhay.twitterdemo.data.model.HomeTimelineTweetModel
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.generic.RoundingParams
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class HomeTimeLineViewHolder internal constructor(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    var tvBodyD: TextView
    var ivProfileImage: SimpleDraweeView
    var tvUserName: TextView
    var tvScreenName: TextView
    var tvCreatedAtD: TextView
    var tvRetweets: TextView
    var tvLikes: TextView

    init {

        tvBodyD = itemView.findViewById(R.id.tvBodyD)
        ivProfileImage = itemView.findViewById(R.id.ivProfileImage)
        tvUserName = itemView.findViewById(R.id.tvUserName)
        tvScreenName = itemView.findViewById(R.id.tvScreenName)
        tvCreatedAtD = itemView.findViewById(R.id.tvCreatedAtD)
        tvRetweets = itemView.findViewById(R.id.tvRetweets)
        tvLikes = itemView.findViewById(R.id.tvLikes)

    }

    fun bindItem(homeTimelineTweetModel: HomeTimelineTweetModel) {
        tvBodyD.text = homeTimelineTweetModel.text
        tvUserName.text = homeTimelineTweetModel.user.name
        tvScreenName.text = "@" + homeTimelineTweetModel.user.screen_name
        tvCreatedAtD.text = formatAgoTime(homeTimelineTweetModel.created_at)
        tvRetweets.text = homeTimelineTweetModel.retweet_count.toString()
        tvLikes.text = homeTimelineTweetModel.favorite_count.toString()
        loadImageCircle(homeTimelineTweetModel.user.profile_image_url, ivProfileImage)
    }

    private fun loadImageCircle(url: String?, targetView: SimpleDraweeView) {
        val request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
            .setProgressiveRenderingEnabled(false)
            .build()
        val controller: DraweeController = Fresco.newDraweeControllerBuilder()
            .setImageRequest(request)
            .setAutoPlayAnimations(false)
            .build()
        val roundingParams = RoundingParams()
        roundingParams.roundAsCircle = true
        targetView.hierarchy.roundingParams = roundingParams
        targetView.controller = controller
    }

    fun formatAgoTime(string: String?): String? {
        val twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy"
        val sf = SimpleDateFormat(twitterFormat, Locale.ENGLISH)
        sf.setLenient(true)
        var relativeDate = ""
        try {
            val dateMillis: Long = sf.parse(string).getTime()
            relativeDate = DateUtils.getRelativeTimeSpanString(
                dateMillis,
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS
            ).toString()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return relativeDate
    }
}