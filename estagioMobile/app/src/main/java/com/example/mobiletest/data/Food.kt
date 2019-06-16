package com.example.mobiletest.data

import java.io.Serializable

class Food(
    var description: String,
    var amount: Float,
    var measure: String,
    var weight: Float,
    var energy: Float,
    var carbohydrate: Float,
    var fat: Float,
    var protein: Float

) : Serializable