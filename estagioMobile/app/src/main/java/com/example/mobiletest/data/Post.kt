package com.example.mobiletest.data

import java.io.Serializable

class Post(
    var feedHash: String,
    var id: Long,
    var profile: Profile,
    var image: String,
    var date: String,
    var energy: Float,
    val carbohydrate: Float,
    val fat: Float,
    val protein: Float,
    var mealType: Int,
    var isLiked: Boolean,
    var foods: MutableList<Food>
) : Serializable