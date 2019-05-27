package com.example.mobiletest.data

class Post(
    var feedHash: String,
    var id: Long,
    var profile: Profile,
    var image: String,
    var date: String,
    var energy: Float,
    var mealType: Int,
    var isLiked: Boolean
)