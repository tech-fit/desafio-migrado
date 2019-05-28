package com.example.mobiletest.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Profile(
    var id: Long,
    var name: String?,
    var image: String,
    @SerializedName("general_goal")
    var generalGoal: String?
) : Serializable