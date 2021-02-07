package com.example.mobiletest.data

import com.google.gson.annotations.SerializedName

class ProfileDetail (
    @SerializedName("t")
    var timestamp: Long = 0,
    @SerializedName("p")
    var page: Int = 0,
    var profile: Profile,
    var items: MutableList<Post>
)