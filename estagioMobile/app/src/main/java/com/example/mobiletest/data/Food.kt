package com.example.mobiletest.data

import java.io.Serializable

data class Food (
    var description: String,
    var measure: String?,
    var amount: Float?,
    var weight: Float?,
    var energy: Float,
    var carbohydrate: Float,
    var fat: Float,
    var protein: Float
): Serializable


