package com.example.mobiletest.data

import java.io.Serializable

class Post(
    var feedHash: String,
    var id: Long,
    var profile: Profile,
    var image: String,
    var date: String,
    var energy: Float,
    var mealType: Int,
    var isLiked: Boolean,
    var totalCarbohydrate: Float,
    var totalProtein: Float,
    var totalFat: Float,
    var foods: MutableList<Food>
) : Serializable