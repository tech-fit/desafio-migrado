package com.example.mobiletest.data

import com.google.gson.annotations.SerializedName

class ProfileDetails(
    @SerializedName("t")
    var timestamp: Long = 0,
    @SerializedName("p")
    var page: Int = 0,
    var items: MutableList<Post>,
    var profile: Profile
)