package com.example.mobiletest.ui.post

import com.example.mobiletest.api.ClientAPI
import com.example.mobiletest.data.Post
import com.example.mobiletest.data.PostDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//Classe repsonsável por fornecer os dados e controlar a lógica para a PostActivity
class PostPresenter {

    private var requesting = false //Flag que controla se existe uma requisição em andamento

    fun getPosts(feedHash: String, onSuccess: (post: Post) -> Unit, onError: () -> Unit) {
        if (!requesting) {
            requesting = true

            //Chamada para a API
            ClientAPI.getService().getPostItem(feedHash).enqueue(object : Callback<PostDetails> {
                override fun onResponse(call: Call<PostDetails>, response: Response<PostDetails>) {
                    val body = response.body()
                    if (response.isSuccessful && body != null) {
                        requesting = false
                        onSuccess(body.item)
                    } else {
                        requesting = false
                        onError()
                    }
                }

                override fun onFailure(call: Call<PostDetails>, t: Throwable) {
                    requesting = false
                    onError()
                }

            })
        }
    }

}