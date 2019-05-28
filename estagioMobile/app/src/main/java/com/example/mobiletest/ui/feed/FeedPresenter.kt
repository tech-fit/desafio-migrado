package com.example.mobiletest.ui.feed

import com.example.mobiletest.api.ClientAPI
import com.example.mobiletest.data.Feed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//Classe repsonsável por fornecer os dados e controlar a lógica para a FeedActivity
class FeedPresenter {

    private var p = 0 //Variável que controla a página atual para requisição
    private var t = 0L //Variável que controla a timestamp para requisição
    private var requesting = false //Flag que controla se existe uma requisição em andamento

    fun getPosts(onSuccess: (feed: Feed) -> Unit, onError: () -> Unit, page: Int? = null, timestamp: Long? = null) {
        if (!requesting) {
            requesting = true

            //Chamada para a API
            ClientAPI.getService().getFeedItems(page, timestamp).enqueue(object : Callback<Feed> {
                override fun onResponse(call: Call<Feed>, response: Response<Feed>) {
                    val feed = response.body()
                    if (response.isSuccessful && feed != null) {
                        p = feed.page
                        t = feed.timestamp
                        requesting = false
                        onSuccess(feed)
                    } else {
                        requesting = false
                        onError()
                    }
                }

                override fun onFailure(call: Call<Feed>, t: Throwable) {
                    requesting = false
                    onError()
                }

            })
        }
    }

    fun getMorePosts(onSuccess: (feed: Feed) -> Unit, onError: () -> Unit) {
        p++ //incremento na variável para buscar a próxima página de posts
        getPosts(onSuccess, onError, p, t)
    }

}