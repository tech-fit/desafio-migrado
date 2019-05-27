package com.example.mobiletest.api

import com.example.mobiletest.data.Feed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPointsAPI {

    companion object{
        const val BASE_URL = "http://api.tecnonutri.com.br/api/v4/"
    }

    @GET("feed")
    fun getFeedItems(@Query("p") p: Int?, @Query("t") t: Long?): Call<Feed>

}