package com.example.mobiletest.ui.post

import com.example.mobiletest.api.ClientAPI
import com.example.mobiletest.data.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostPresenter(feedHash: String) {
    private var requesting = false

    fun getPostDetails(onSuccess: (post: Post) -> Unit, onError: () -> Unit, feedHash: String) {
        if (!requesting) {
            requesting = true

            //Chamada para a API
            ClientAPI.getService().getPostDetails(feedHash).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    val post = response.body()
                    if (response.isSuccessful && post != null) {
                        requesting = false
                        onSuccess(post)
                    } else {
                        requesting = false
                        onError()
                    }
                }
                override fun onFailure(call: Call<Post>, t: Throwable) {
                    requesting = false
                    onError()
                }

            })
        }
    }
}