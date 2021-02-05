package com.example.mobiletest.api

import com.example.mobiletest.data.Feed
import com.example.mobiletest.data.Post
import com.example.mobiletest.data.PostDetail
import com.example.mobiletest.data.ProfileDetail
import retrofit2.Call
import retrofit2.http.*

interface EndPointsAPI {

    companion object{
        const val BASE_URL = "http://api.tecnonutri.com.br/api/v4/"
        const val HEADER = "X-API-KEY: OefGRsBYZo6j9c8QV11habTsmCmsGA6Oomk0bvTLhjtOJafFsg7C0yyboDlUrkuo"
    }

    @GET("feed")
    fun getFeedItems(@Query("p") p: Int?, @Query("t") t: Long?): Call<Feed>

    @Headers(HEADER)
    @GET("feed/{feedHash}")
    fun getPostDetail(@Path("feedHash") feedHash: String): Call<PostDetail>

    @Headers(HEADER)
    @GET("profile/{id}")
    fun getProfileDetail(@Path("id") id: Long, @Query("p") p: Int?, @Query("t") t: Long?): Call<ProfileDetail>
}