package com.example.mobiletest.api

import com.example.mobiletest.data.Feed
import com.example.mobiletest.data.PostDetails
import com.example.mobiletest.data.ProfileDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndPointsAPI {

    companion object {
        const val BASE_URL = "http://api.tecnonutri.com.br/api/test/"
    }

    @GET("feed")
    fun getFeedItems(@Query("p") p: Int?, @Query("t") t: Long?): Call<Feed>

    @GET("feed/{feedHash}")
    fun getPostItem(@Path("feedHash") feedHash: String): Call<PostDetails>


    @GET("profile/{id}")
    fun getProfile(@Path("id") id: Long, @Query("p") p: Int?, @Query("t") t: Long?): Call<ProfileDetails>

}