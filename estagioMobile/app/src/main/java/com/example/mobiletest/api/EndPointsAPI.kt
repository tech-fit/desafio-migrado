package com.example.mobiletest.api

import com.example.mobiletest.data.Feed
import com.example.mobiletest.data.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndPointsAPI {

    companion object{
        const val BASE_URL = "http://api.tecnonutri.com.br/api/v4/"
    }

    @GET("feed")
    fun getFeedItems(@Query("p") p: Int?, @Query("t") t: Long?): Call<Feed>

    @GET("profile/{id}")
    fun getProfilePosts(@Path("id") id: Long, @Query("p") p: Int?, @Query("t") t: Long?): Call<Feed>

    @GET("feed/{feedHash}")
    fun getPostDetails(@Path("feedHash") feedHash: String): Call<Post>

}