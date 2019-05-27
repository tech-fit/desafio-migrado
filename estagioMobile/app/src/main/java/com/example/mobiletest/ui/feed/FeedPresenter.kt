package com.example.mobiletest.ui.feed

import com.example.mobiletest.api.ClientAPI
import com.example.mobiletest.data.Feed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedPresenter {

    fun getPosts(onSuccess: (feed: Feed)->Unit, onError: ()->Unit){
        ClientAPI.getService().getFeedItems(null, null).enqueue(object : Callback<Feed>{

            override fun onResponse(call: Call<Feed>, response: Response<Feed>) {
                val feed = response.body()
                if (response.isSuccessful && feed != null){
                    onSuccess(feed)
                }else{
                    onError()
                }
            }

            override fun onFailure(call: Call<Feed>, t: Throwable) {
                onError()
            }

        })
    }

}