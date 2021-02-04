package com.example.mobiletest.ui.post

import com.example.mobiletest.api.ClientAPI
import com.example.mobiletest.data.Feed
import com.example.mobiletest.data.Post
import com.example.mobiletest.data.PostDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostPresenter {

    fun getPostDetail(onSuccess: (post: Post) -> Unit, onError: () -> Unit, feedhash: String) {

            //Chamada para a API
            ClientAPI.getService().getPostDetail(feedhash).enqueue(object : Callback<PostDetail> {
                override fun onResponse(call: Call<PostDetail>, response: Response<PostDetail>) {
                    val post = response.body()
                    if (response.isSuccessful && post != null) {
                        onSuccess(post.item)
                    } else {
                        onError()
                    }
                }

                override fun onFailure(call: Call<PostDetail>, t: Throwable) {
                    onError()
                }

            })
    }

}